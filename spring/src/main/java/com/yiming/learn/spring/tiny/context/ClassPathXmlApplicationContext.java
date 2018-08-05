package com.yiming.learn.spring.tiny.context;

import com.yiming.learn.spring.tiny.BeanDefinition;
import com.yiming.learn.spring.tiny.beans.factory.AbstractBeanFactory;
import com.yiming.learn.spring.tiny.beans.factory.AutowireCapableBeanFactory;
import com.yiming.learn.spring.tiny.beans.io.ResourceLoader;
import com.yiming.learn.spring.tiny.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 16:30.
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> entry : reader.getRegistry().entrySet()) {
            beanFactory.registryBeanDefinition(entry.getKey(), entry.getValue());
        }
    }
}
