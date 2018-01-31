# 如何获取AOP代理对象 #
## 方法一 ##
- 通过ThreadLocal 获取

AopContext.currentProxy();

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void child() {

        userMapper.save("zhaobuzhu", 12);
        throw new RuntimeException();
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void father() {

        try {
            UserService userService = (UserService) AopContext.currentProxy();
            userService.child();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userMapper.save("wangbaobao", 12);
    }


- 使用AOP 代理后的方法调用执行流程

>     
1. 调用者调用代理对象，通过 ThreadLocal 暴露代理对象, AopContext.setCurrentProxy(proxy) ，并保存上次代理对象 oldProxy 。
2. 开启事物。
3. 进行其他代理操作。
4. 调用目标对象的目标方法。
5. 目标方法调用返回。
6. 进行其他代理操作。
7. 关闭事物。
8. 还原 ThreadLocal 绑定的代理对象为上次代理对象 AopContext.setCurrentProxy(oldProxy) 。	返回至调用处。
## 方法二 ##
- 通过初始化方法在目标对象中注入代理对象

> 

	@Autowired
    private ApplicationContext context;

    private UserService userServiceProxy;

    @PostConstruct
    public void setUserServiceProxy() {

        this.userServiceProxy = context.getBean(UserService.class);
    }

    @Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void father2() {
	
	    try {
	        userServiceProxy.child();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    userMapper.save("wangbaobao", 12);
	}


- 如果存在循环依赖注入，可能会出现问题。

## 方法三 ##

- 通过 BeanPostProcessor 在目标对象中注入代理对象

 
  1. 定义BeanPostProcessor 需要使用的标识接口

			public interface BeanSelfAware {
	
			    /**
			     * 注入自己的属性
			     * @param bean
			     */
			    void setSelf(Object bean);
			}
  2. Bean 实现标识接口

			@Service
			@Transactional(isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
			public class UserServiceImpl implements UserService, BeanSelfAware {
				private UserService self;
			
			    @Override
			    public void father3() {
			
			        try {
			            self.child();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        userMapper.save("wangbaobao", 12);
			
			    }
			
			    @Override
			    public void setSelf(Object self) {
			        this.self = (UserService) self;
			    }
			}
 3. 定义类实现 BeanPostProcessor 

			public class InjectBeanSelfProcessor implements BeanPostProcessor {
			    @Override
			    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
			        return null;
			    }
			
			    @Override
			    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
			
			        if (o instanceof BeanSelfAware) {
			            System.out.println("inject proxy" + o.getClass());
			            BeanSelfAware myBean = (BeanSelfAware) o;
			            myBean.setSelf(o);
			            return myBean;
			        }
			        return o;
			    }
			}

- 可能不支持循环依赖，可能出现问题。