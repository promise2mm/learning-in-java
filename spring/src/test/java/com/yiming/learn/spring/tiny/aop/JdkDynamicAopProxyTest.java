package com.yiming.learn.spring.tiny.aop;

import com.yiming.learn.spring.tiny.HelloWorldService;
import com.yiming.learn.spring.tiny.context.ApplicationContext;
import com.yiming.learn.spring.tiny.context.ClassPathXmlApplicationContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 18:05.
 */
public class JdkDynamicAopProxyTest {

    @Test
    public void test() throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("helloWorldService");
        helloWorldService.sayHello();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(helloWorldService, HelloWorldService.class));

        MethodInterceptor methodInterceptor = new TimmerInterceptor();
        advisedSupport.setMethodInterceptor(methodInterceptor);

        JdkDynamicAopProxy proxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) proxy.getProxy();

        helloWorldServiceProxy.sayHello();

    }

}
