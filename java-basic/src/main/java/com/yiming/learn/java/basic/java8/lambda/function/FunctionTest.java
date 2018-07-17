package com.yiming.learn.java.basic.java8.lambda.function;

import java.util.function.Function;
import org.junit.Test;

/**
 * Created by yiming on 2018-07-17 14:37. Description:
 *
 * @author yiming
 */
public class FunctionTest {

    /**
     *
     */
    @Test
    public void testFunction() {

        String javaStr = " hello.java ";

        Function<String, String> endWithJava = s -> s.endsWith(".java") ? s : "";
        Function<String, String> trimFunction = String::trim;

        // 首尾有空格, endsWith(".java") 返回false 故输出为空字符串
        System.out.println("1. " + endWithJava.apply(javaStr));

        // apply 之前先调用compose - "hello.java"
        System.out.println("2. " + endWithJava.compose(trimFunction).apply(javaStr));

        // 先执行compose, 再执行apply, 最后执行andThen - hello.java(10)
        System.out.println("3. " + endWithJava.compose(trimFunction).andThen(s -> s + "(" + s.length() + ")").apply(javaStr));

        System.out.println("4. " + Function.identity().compose(trimFunction).andThen(s -> s + "(?)").apply(javaStr));

        // identity()是静态方法, 是一个恒等方法, 返回一个给定x参数返回x作为结果的Function(x -> x)
        // 返回一个执行了apply()方法之后只会返回输入参数的函数对象
        System.out.println(Function.identity().apply("hello"));
    }

}
