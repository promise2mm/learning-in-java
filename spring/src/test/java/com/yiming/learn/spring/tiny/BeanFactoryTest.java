package com.yiming.learn.spring.tiny;

import com.yiming.learn.spring.tiny.beans.factory.AbstractBeanFactory;
import com.yiming.learn.spring.tiny.beans.factory.AutowireCapableBeanFactory;
import com.yiming.learn.spring.tiny.beans.io.ResourceLoader;
import com.yiming.learn.spring.tiny.beans.xml.XmlBeanDefinitionReader;
import org.junit.Test;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-27 17:14.
 */
public class BeanFactoryTest {

    @Test
    public void testLazy() throws Exception {

        // 1. 从xml文件读取bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("tinyioc.xml");

        // 2. 初始化工厂
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        reader.getRegistry().forEach(beanFactory::registryBeanDefinition);

        // 3. 获取并使用bean
        HelloWorldServiceImpl helloWorldService = (HelloWorldServiceImpl) beanFactory.getBean("helloWorldService");
        helloWorldService.sayHello();
    }

    @Test
    public void testPreInstantiateSingletons() throws Exception {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("tinyioc.xml");

        AbstractBeanFactory factory = new AutowireCapableBeanFactory();
        reader.getRegistry().forEach(factory::registryBeanDefinition);

        factory.preInstantiateSingletons();

        HelloWorldServiceImpl helloWorldService = (HelloWorldServiceImpl) factory.getBean("helloWorldService");
        helloWorldService.sayHello();
    }

}
