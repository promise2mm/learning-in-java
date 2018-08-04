package com.yiming.learn.spring.tiny;

import java.util.Random;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-27 17:15.
 */
public class OutputService {

    public void sayHello(String text) {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("OutputService: Hello " + text + "!");
    }

}
