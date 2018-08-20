package com.yiming.learn.spring.tiny.aop;

import org.aopalliance.aop.Advice;

/**
 * Created by yiming on 2018/8/5.
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    public AspectJExpressionPointcutAdvisor() {
        System.out.println("AspectJExpressionPointcutAdvisor");
    }


    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
