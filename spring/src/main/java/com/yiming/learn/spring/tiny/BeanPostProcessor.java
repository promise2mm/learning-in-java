package com.yiming.learn.spring.tiny;

/**
 * Created by yiming on 2018/8/5.
 */
public interface BeanPostProcessor {

    Object postProcessBeforeBeanInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterBeanInitialization(Object bean, String beanName) throws Exception;

}
