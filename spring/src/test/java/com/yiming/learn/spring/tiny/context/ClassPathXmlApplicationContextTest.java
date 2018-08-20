package com.yiming.learn.spring.tiny.context;

import com.yiming.learn.spring.tiny.HelloWorldService;
import com.yiming.learn.spring.tiny.HelloWorldServiceImpl;
import org.junit.Test;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 16:42.
 */
public class ClassPathXmlApplicationContextTest {

    @Test
    public void test() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldServiceImpl helloWorldService = (HelloWorldServiceImpl) context.getBean("helloWorldService");
        helloWorldService.sayHello();
    }

    @Test
    public void testPostBeanProcessor() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc-post-bean-processor.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.sayHello();
    }

}
