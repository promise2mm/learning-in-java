package com.yiming.learn.java.basic.exception;

import com.alibaba.fastjson.JSON;

/**
 * Created by yiming on 2018-04-28 10:29.
 * Description:
 *
 * @author <a href="mailto:nishibin@cai-inc.com"></a>
 */

public class ExceptionDemo {


    public static void main(String[] args) {
        try {
            new ExceptionDemo.ExceptionThread().run();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(JSON.toJSON(e.getStackTrace()));
        }
    }

    static class ExceptionThread implements Runnable {
        @Override
        public void run() {
            throw new NullPointerException();
        }
    }

}

