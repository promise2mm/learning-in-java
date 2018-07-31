package com.yiming.learn.spring.tiny;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-27 17:15.
 */
public class HelloWorldServiceImpl implements HelloWorldService {

    private String text;

    private OutputService outputService;

    @Override
    public void sayHello() {
        outputService.sayHello(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}
