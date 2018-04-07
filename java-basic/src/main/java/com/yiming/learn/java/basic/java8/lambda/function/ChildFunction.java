package com.yiming.learn.java.basic.java8.lambda.function;

/**
 * Created by yiming on 2018/1/18.
 */
public class ChildFunction implements BaseFunction {

    @Override
    public boolean test(String s) {
        return s.endsWith(".java");
    }

}
