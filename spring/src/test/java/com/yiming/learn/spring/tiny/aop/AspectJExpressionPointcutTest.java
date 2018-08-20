package com.yiming.learn.spring.tiny.aop;

import com.yiming.learn.spring.tiny.HelloWorldService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yiming on 2018/8/5.
 */
public class AspectJExpressionPointcutTest {


    @Test
    public void testClassFilter() {
        String expression = "execution(* com.yiming.learn.spring.tiny.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldService.class);
        Assert.assertTrue(matches);
    }

    @Test
    public void testMethodInterceptor() throws NoSuchMethodException {
        String expression = "execution(* com.yiming.learn.spring.tiny.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean sayHello = aspectJExpressionPointcut.getMethodMatcher()
                .matches(HelloWorldService.class.getMethod("sayHello"), HelloWorldService.class);
        Assert.assertTrue(sayHello);
    }

}
