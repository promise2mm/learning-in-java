package com.yiming.learn.spring.tiny;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-27 17:15.
 */
public class OutputService {

    private HelloWorldService helloWorldService;

    public void sayHello(String text) {
        System.out.println("OutputService: Hello " + text + "!");
    }

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
}
