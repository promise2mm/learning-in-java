package com.yiming.learn.spring.tiny.context;

import com.yiming.learn.spring.tiny.BeanPostProcessor;
import com.yiming.learn.spring.tiny.beans.factory.AbstractBeanFactory;
import com.yiming.learn.spring.tiny.beans.factory.BeanFactory;

import java.util.List;

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
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessor(beanFactory);
        onRefresh();
    }

    protected void onRefresh() throws Exception {
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    protected void registerBeanPostProcessor(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }
}
