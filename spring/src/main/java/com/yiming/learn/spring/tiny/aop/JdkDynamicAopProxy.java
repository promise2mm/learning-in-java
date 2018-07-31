package com.yiming.learn.spring.tiny.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 基于jdk的动态代理
 *
 * @author yiming
 * @since 2018-07-31 17:41.
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(),
            new Class[]{advisedSupport.getTargetSource().getTargetClass()}, this);
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
        return methodInterceptor.invoke(
            new ReflectMethodInvocation(
                advisedSupport.getTargetSource().getTarget(), method, args));
    }
}
