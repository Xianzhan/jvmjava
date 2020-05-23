package com.github.xianzhan.jvmjava.java.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * IO 工具类
 *
 * @author xianzhan
 * @since 2020-05-23
 */
public class IOUtils {

    public static byte[] readBytes(InputStream is, int length) throws IOException {
        byte[] bytes = new byte[length];
        int read = is.read(bytes);
        return bytes;
    }
}
