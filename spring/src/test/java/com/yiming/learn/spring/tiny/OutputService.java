package com.yiming.learn.spring.tiny;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-27 17:15.
 */
public class OutputService {

    private String text;

    public void sayHello() {
        System.out.println("Hello " + text + "!");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
