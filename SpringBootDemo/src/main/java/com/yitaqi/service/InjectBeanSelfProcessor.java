package com.yitaqi.service;

import com.yitaqi.inter.BeanSelfAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * BeanPostProcessor 注入对象 初始化和后续动作
 * @author xue
 */
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
