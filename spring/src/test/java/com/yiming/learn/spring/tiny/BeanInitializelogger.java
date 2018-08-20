package com.yiming.learn.spring.tiny;

/**
 * Created by yiming on 2018/8/5.
 */
public class BeanInitializeLogger implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeBeanInitialization(Object bean, String beanName) {
        System.out.println("Initialize bean [" + beanName + "] start.");
        return bean;
    }

    @Override
    public Object postProcessAfterBeanInitialization(Object bean, String beanName) {
        System.out.println("Initialize bean [" + beanName + "] end.");
        return bean;
    }
}
