package com.yiming.learn.spring.tiny.xml;

import com.yiming.learn.spring.tiny.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 15:09.
 */
public class XmlBeanDefinitionReaderTest {

    @Test
    public void test() throws Exception {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("tinyioc.xml");
        int size = reader.getRegistry().size();
        Assert.assertTrue(size > 0);
    }

}
