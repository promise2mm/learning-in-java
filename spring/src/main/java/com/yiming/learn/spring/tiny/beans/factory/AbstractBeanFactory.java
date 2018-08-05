package com.yiming.learn.spring.tiny.beans.factory;

import com.yiming.learn.spring.tiny.BeanDefinition;
import com.yiming.learn.spring.tiny.BeanPostProcessor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yiming
 * @since 2018-07-27 17:37.
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<String> beanNames = new ArrayList<>();


    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * 获取bean
     *
     * @param name bean名称
     * @return bean对象
     */
    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("Not found bean [" + name + "]!");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean, name);
        }
        return bean;
    }

    protected Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            beanPostProcessor.postProcessBeforeBeanInitialization(bean, name);
        }
        // todo call initialize method

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            beanPostProcessor.postProcessAfterBeanInitialization(bean, name);
        }
        return bean;
    }

    public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
        beanPostProcessors.add(postProcessor);
    }

    public List getBeansForType(Class type) throws Exception {
        List<Object> beans = new ArrayList<>();
        for (String beanName : beanNames) {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanName).getClass())) {
                beans.add(getBean(beanName));
            }
        }
        return beans;
    }


    /**
     * 注册bean
     *
     * @param name           bean名称
     * @param beanDefinition bean定义
     */
    public void registryBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name, beanDefinition);
        beanNames.add(name);
    }

    public void preInstantiateSingletons() throws Exception {
        for (Iterator iterator = beanNames.iterator(); iterator.hasNext(); ) {
            String beanName = (String) iterator.next();
            getBean(beanName);
        }
    }

    /**
     * 初始化bean
     *
     * @param beanDefinition bean定义
     * @return bean实例
     */
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        // 子类实现具体细节
    }


    protected Object createInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();

    }
}
