package com.yiming.learn.spring.tiny.beans.io;

import java.io.InputStream;

/**
 * Resource是内部定位资源的接口
 *
 * @author yiming
 * @since 2018-07-31 14:27.
 */
public interface Resource {

    InputStream getInputStream() throws Exception;
}
