package com.yiming.learn.spring.tiny.factory;

import com.yiming.learn.spring.tiny.BeanDefinition;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yiming
 * @since 2018-07-27 17:37.
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<String> beanNames = new ArrayList<>();

    /**
     * 获取bean
     *
     * @param name bean名称
     * @return bean对象
     */
    @Override
    public Object getBean(String name) {
        Object bean = beanDefinitionMap.get(name).getBean();
        if (bean == null) {
            throw new IllegalArgumentException("Not found bean [" + name + "]!");
        }
        return bean;
    }

    /**
     * 注册bean
     *
     * @param name bean名称
     * @param beanDefinition bean定义
     */
    @Override
    public void registryBeanDefinition(String name, BeanDefinition beanDefinition) {
        Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(name, beanDefinition);
        beanNames.add(name);
    }

    public void preInstantiateSingletons() {
        for (String beanName : beanNames) {
            getBean(beanName);
        }
    }

    /**
     * 初始化bean
     *
     * @param beanDefinition bean定义
     * @return bean实例
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition);
}
