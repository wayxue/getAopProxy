package com.yitaqi.inter;

/**
 * 注入自己的属性
 * @author xue
 */
public interface BeanSelfAware {

    /**
     * 注入自己的属性
     * @param bean
     */
    void setSelf(Object bean);
}
