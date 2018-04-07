package com.yiming.learn.jvm;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by andyni on 2018/4/7.
 */
public class OOMTest {


    @Test
    public void testOOM() {

        List<Object> list = Lists.newArrayList();
        while (true) {
            list.add(new Object());
        }

    }

}
