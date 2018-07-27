package com.yiming.learn.spring.tiny;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-27 17:09.
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
