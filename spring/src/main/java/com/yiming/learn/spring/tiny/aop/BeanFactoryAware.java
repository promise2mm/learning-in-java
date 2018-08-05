package com.yiming.learn.spring.tiny.aop;

import com.yiming.learn.spring.tiny.beans.factory.BeanFactory;

/**
 * Created by yiming on 2018/8/5.
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;

}
