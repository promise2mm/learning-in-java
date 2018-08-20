package com.yiming.learn.spring.tiny.aop;

/**
 * Created by yiming on 2018/8/5.
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
