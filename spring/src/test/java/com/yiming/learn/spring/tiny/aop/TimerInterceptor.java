package com.yiming.learn.spring.tiny.aop;

import java.time.Instant;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 18:06.
 */
public class TimerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = Instant.now().toEpochMilli();
        Object res = methodInvocation.proceed();
        System.out.println("cost " + (Instant.now().toEpochMilli() - start) + " millis.");
        return res;
    }
}
