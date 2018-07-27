package com.yiming.learn.spring.tiny;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean工厂类
 * 提供 注册和获取bean方法
 *
 * @author yiming
 * @since 2018-07-27 17:10.
 */
public class BeanFactory {

    private Map<String, BeanDefinition> registryMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        return registryMap.get(name).getBean();
    }

    public void registryBean(String name, BeanDefinition beanDefinition) {
        registryMap.put(name, beanDefinition);
    }

}
