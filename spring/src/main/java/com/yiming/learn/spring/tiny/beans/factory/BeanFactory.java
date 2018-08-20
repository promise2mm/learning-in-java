package com.yiming.learn.spring.tiny.beans.factory;

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
    Object getBean(String name) throws Exception;

}
