package com.yiming.learn.spring.tiny.io;

import java.net.URL;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 14:29.
 */
public class ResourceLoader {

    public Resource getResource(String location) {
        URL resource = getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }

}
