package com.yiming.learn.spring.tiny.aop;

/**
 * Created by yiming on 2018/8/4.
 */
public interface ClassFilter {

    boolean matches(Class targetClass);

}
