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

        // 普通调用, 未使用AOP
        ApplicationContext context = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("helloWorldService");
        //helloWorldService.sayHello();

        // 1. 设置被代理对象
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(helloWorldService, HelloWorldService.class));

        // 2. 为代理对象设置拦截器
        MethodInterceptor methodInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(methodInterceptor);

        // 3. 创建代理
        JdkDynamicAopProxy proxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) proxy.getProxy();

        // 4. 基于AOP调用
        helloWorldServiceProxy.sayHello();

    }

}
