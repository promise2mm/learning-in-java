package com.yiming.learn.spring.tiny.factory;

import com.yiming.learn.spring.tiny.BeanDefinition;

/**
 * Bean工厂类 提供 注册和获取bean方法
 *
 * @author yiming
 * @since 2018-07-27 17:10.
 */
public interface BeanFactory {

    /**
     * 获取bean
     *
     * @param name bean名称
     * @return bean对象
     */
    Object getBean(String name);

    /**
     * 注册bean
     *
     * @param name bean名称
     * @param beanDefinition bean定义
     */
    void registryBeanDefinition(String name, BeanDefinition beanDefinition);

}
