package com.yiming.learn.spring.tiny.beans.io;

import java.io.InputStream;
import org.junit.Assert;
import org.junit.Test;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 14:39.
 */
public class ResourceLoaderTest {

    @Test
    public void test() throws Exception {
        String resourceFile = "tinyioc.xml";
        Resource resource = new ResourceLoader().getResource(resourceFile);
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }

}
