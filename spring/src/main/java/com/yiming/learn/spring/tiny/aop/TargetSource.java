package com.yiming.learn.spring.tiny.aop;

/**
 * 被代理的对象封装
 *
 * @author yiming
 * @since 2018-07-31 17:54.
 */
public class TargetSource {

    /**
     * 被代理类
     */
    private Class<?>[] targetClass;

    /**
     * 被代理实际对象
     */
    private Object target;

    public TargetSource(Object target, Class<?>... targetClass) {
        this.target = target;
        this.targetClass = targetClass;
    }

    public Class<?>[] getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Object target, Class<?>[] targetClass) {
        this.target = target;
        this.targetClass = targetClass;
    }

    public Object getTarget() {
        return target;
    }

}
