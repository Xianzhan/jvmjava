package com.github.xianzhan.jvmjava.java.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
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

    public static int readPadding(DataInputStream dis) {
        if (dis instanceof PaddingDataInputStream pis) {
            return pis.readPadding();
        }
        return 0;
    }

    public static PaddingDataInputStream newPaddingDataInputStream(byte[] bytes) {
        return new PaddingDataInputStream(new PosByteArrayInputStream(bytes));
    }

    private static class PaddingDataInputStream extends DataInputStream {

        public PaddingDataInputStream(PosByteArrayInputStream in) {
            super(in);
        }

        public int readPadding() {
            PosByteArrayInputStream pis = (PosByteArrayInputStream) in;
            int sum = 0;
            while (pis.getPos() % 4 != 0) {
                var read = pis.read();
                sum++;
            }
            return sum;
        }
    }

    private static class PosByteArrayInputStream extends ByteArrayInputStream {

        public PosByteArrayInputStream(byte[] buf) {
            super(buf);
        }

        public int getPos() {
            return pos;
        }
    }
}
