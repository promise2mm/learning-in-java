package com.yiming.learn.spring.tiny.context;

import com.yiming.learn.spring.tiny.beans.factory.AbstractBeanFactory;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 16:28.
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {
    }

    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }
}
