package com.yitaqi.service;

import com.yitaqi.dao.UserMapper;
import com.yitaqi.inter.BeanSelfAware;
import com.yitaqi.pojo.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author xue
 */
@Service
@Transactional(isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService, BeanSelfAware {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUser(Integer age) {

        return userMapper.getUser(age);
    }

    @Override
    public void save(String name, Integer age) {

        userMapper.save(name, age);
    }

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


    @Autowired
    private ApplicationContext context;

    private UserService userServiceProxy;

    @PostConstruct
    public void setUserServiceProxy() {

        this.userServiceProxy = context.getBean(UserService.class);
    }

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
