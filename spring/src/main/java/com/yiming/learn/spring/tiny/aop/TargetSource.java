package com.yiming.learn.spring.tiny.aop;

/**
 * 被代理的对象
 *
 * @author yiming
 * @since 2018-07-31 17:54.
 */
public class TargetSource {

    private Class targetClass;

    private Object target;

    public TargetSource(Object target, Class targetClass) {
        this.target = target;
        this.targetClass = targetClass;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
