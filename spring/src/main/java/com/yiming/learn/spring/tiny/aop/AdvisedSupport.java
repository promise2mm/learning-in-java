package com.yiming.learn.spring.tiny.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 17:43.
 */
public class AdvisedSupport {

    /**
     * 代理目标对象
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }
}
