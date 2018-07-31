package com.yiming.learn.spring.test;

import com.yiming.learn.spring.tiny.BeanDefinition;
import com.yiming.learn.spring.tiny.PropertyValue;
import com.yiming.learn.spring.tiny.PropertyValues;
import com.yiming.learn.spring.tiny.factory.AutowireCapableBeanFactory;
import com.yiming.learn.spring.tiny.factory.BeanFactory;
import org.junit.Test;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-27 17:14.
 */
public class BeanFactoryTest {

    @Test
    public void test() {
        // 1. 初始化工厂
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        // 2. 设置属性
        PropertyValue pv = new PropertyValue("text", "yiming");
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(pv);

        // 3. 定义并注册bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setPropertyValues(propertyValues);
        beanDefinition.setBeanClassName("com.yiming.learn.spring.test.HelloWorldService");

        beanFactory.registryBeanDefinition("helloWorldService", beanDefinition);

        // 4. 获取并使用bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.sayHello();
    }

}
