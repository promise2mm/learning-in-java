package com.yiming.learn.spring.test;

import com.yiming.learn.spring.tiny.BeanDefinition;
import com.yiming.learn.spring.tiny.BeanFactory;
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
        BeanFactory beanFactory = new BeanFactory();

        // 2. 定义并注册bean
        BeanDefinition beanDefinition = new BeanDefinition(new HelloWorldService());
        beanFactory.registryBean("helloWorldService", beanDefinition);

        // 3. 获取并使用bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.sayHello();
    }

}
