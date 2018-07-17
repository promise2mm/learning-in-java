package com.yiming.learn.java.basic.java8.lambda.function;

/**
 *
 * @author yiming
 * @date 2018/1/18
 */
public class StringFunction implements BaseFunction {

    @Override
    public boolean test(String s) {
        return s.endsWith(".java");
    }

}
