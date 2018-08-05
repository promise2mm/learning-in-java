package com.yiming.learn.spring.tiny.aop;

import org.aopalliance.aop.Advice;

/**
 * Created by yiming on 2018/8/5.
 */
public interface Advisor {

    Advice getAdvice();

}
