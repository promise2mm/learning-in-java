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
public class TimmerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        int start = Instant.now().getNano();
        Object res = methodInvocation.proceed();
        System.out.println("cost " + (Instant.now().getNano() - start) + " nanos.");
        return res;
    }
}
