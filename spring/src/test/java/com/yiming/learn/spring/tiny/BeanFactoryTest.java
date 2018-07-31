package com.yiming.learn.spring.tiny;

import com.yiming.learn.spring.tiny.factory.AutowireCapableBeanFactory;
import com.yiming.learn.spring.tiny.factory.BeanFactory;
import com.yiming.learn.spring.tiny.io.ResourceLoader;
import com.yiming.learn.spring.tiny.xml.XmlBeanDefinitionReader;
import org.junit.Test;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-27 17:14.
 */
public class BeanFactoryTest {

    @Test
    public void test() throws Exception {

        // 1. 从xml文件读取bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("tinyioc.xml");

        // 2. 初始化工厂
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        reader.getRegistry().forEach(beanFactory::registryBeanDefinition);

        // 3. 获取并使用bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.sayHello();
    }

}
