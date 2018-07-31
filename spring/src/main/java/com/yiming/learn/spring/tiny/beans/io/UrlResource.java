package com.yiming.learn.spring.tiny.beans.io;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 14:29.
 */
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        URLConnection urlConnection = url.openConnection();
        return urlConnection.getInputStream();
    }
}
