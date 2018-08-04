package com.yiming.learn.spring.tiny.aop;

import java.lang.reflect.Method;

/**
 * Created by yiming on 2018/8/4.
 */
public interface MethodMatcher {

    boolean matches(Method method, Class targetClass);

}
