package com.yiming.java8.optional;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yiming on 2018/1/11.
 */
public class OptionalTest {

    private static final ConcurrentHashMap<String, String> CACHE = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        String key = "cacheKey";
//        CACHE.put(key, "cacheValue");
//        key = "dbkey";
        String x = get(key);
        System.out.println(x);
    }

    private static Optional<String> getFromDb(final String key) {
        if ("dbkey".equals(key)) {
            return Optional.ofNullable("dbvalue");
        }
        return Optional.ofNullable(null);
    }

    private static Optional<String> getFromCache(final String key) {
        return Optional.ofNullable(CACHE.get(key));
    }

    private static String get(String key) {
        return getFromCache(key).orElse(getFromDb(key).orElse(null));
    }

}
