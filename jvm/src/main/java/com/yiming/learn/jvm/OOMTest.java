package com.yiming.learn.jvm;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by yiming on 2018/4/7.
 */
public class OOMTest {


    @Test
    public void testOOM() {

        List<Object> list = Lists.newArrayList();
        while (true) {
            list.add(new Object());
        }

    }

    /**
     *
     */
    @Test
    public void testStackOverFlow() {
        new StackOverFlowObj().incre(1);
    }

    class StackOverFlowObj {

        private int id;

        int incre(int id) {
            return incre(++id);
        }
    }
    @Test
    public void test() {
        System.out.println("msg from dev");
    }

}
