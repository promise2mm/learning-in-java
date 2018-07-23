package com.yiming.learn.java.basic.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Tables;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yiming on 2018/7/23.
 */
public class CollectionTest {

    @Test
    public void testAsList() {

        List<String> list = Lists.newArrayList("Hello", "World");

        String[] arr = new String[]{"Hello", "World"};

        List<String> asList = Arrays.asList(arr);
        // java.lang.UnsupportedOperationException
        // asList.add("?");

        arr[0] = "???";
        // 更新了数组的值会导致asList中的值被更新 - ["???","World"]
        System.out.println(JSON.toJSONString(asList));


    }

    @Test
    public void testMap() {
        Object o = new Object();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(null, null);

        Hashtable<String, Object> hashtable = new Hashtable<>();
        // java.lang.NullPointerException
        // hashtable.put(null, null);

        // java.lang.NullPointerException
        // hashtable.put("", null);

        hashtable.put("", o);


        TreeMap<String, Object> treeMap = new TreeMap<>();
        // java.lang.NullPointerException
        // treeMap.put(null, null);

        treeMap.put("", null);


        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        // java.lang.NullPointerException
        // concurrentHashMap.put("", null);
        concurrentHashMap.put("", o);



    }

}
