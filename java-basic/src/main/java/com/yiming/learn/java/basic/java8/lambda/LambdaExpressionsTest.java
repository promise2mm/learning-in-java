package com.yiming.learn.java.basic.java8.lambda;

import com.yiming.learn.java.basic.java8.lambda.function.BaseFunction;
import java.io.File;
import java.util.Arrays;

/**
 *
 * @author yiming
 * @date 2018/1/18
 */
public class LambdaExpressionsTest {

    public static void main(String[] args) {

//        FileFilter isXml = (File f) -> f.getName().endsWith(".xml");
//        boolean res = isXml.accept(new File("./hello.xmld"));
//        System.out.println(res);
//
        BaseFunction function = (String s) -> s.endsWith(".java");
        boolean res = function.test("ss.java");
        System.out.println(res);

        File file = new File("/Users/yiming/newWorld/learning-in-java/src/main/java/com/yiming/java8/lambda/function");
        Arrays.stream(file.listFiles()).filter(f -> f.getName().endsWith(".java")).map(File::getName).forEach(System.out::println);
        //System.out.println(file.isDirectory());

    }

}
