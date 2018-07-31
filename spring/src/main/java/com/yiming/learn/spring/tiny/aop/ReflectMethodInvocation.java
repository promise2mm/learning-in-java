package com.yiming.learn.spring.tiny.aop;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInvocation;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 17:47.
 */
public class ReflectMethodInvocation implements MethodInvocation {

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 方法对象
     */
    private Method method;

    /**
     * 参数
     */
    private Object[] args;

    public ReflectMethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
