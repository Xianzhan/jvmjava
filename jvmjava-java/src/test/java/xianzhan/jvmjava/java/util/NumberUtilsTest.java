package xianzhan.jvmjava.java.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author xianzhan
 * @since 2020-05-24
 */
public class NumberUtilsTest {

    @Test
    public void testHighInt() {
        long l;
        int high;

        l = 0;
        high = NumberUtils.highInt(l);
        Assertions.assertEquals(0, high);

        l = 1;
        high = NumberUtils.highInt(l);
        Assertions.assertEquals(0, high);

        l = 0b000000000000000000000000000000100000000000000000000000000000000L;
        high = NumberUtils.highInt(l);
        Assertions.assertEquals(1, high);

        l = 0x8000000000000000L;
        high = NumberUtils.highInt(l);
        Assertions.assertEquals(Integer.MIN_VALUE, high);
    }

    @Test
    public void testLowInt() {
        long l;
        int low;

        l = 0;
        low = NumberUtils.lowInt(l);
        Assertions.assertEquals(0, low);

        l = 1;
        low = NumberUtils.lowInt(l);
        Assertions.assertEquals(1, low);

        l = 0b000000000000000000000000000000100000000000000000000000000000000L;
        low = NumberUtils.lowInt(l);
        Assertions.assertEquals(0, low);

        l = 0x800000007FFFFFFFL;
        low = NumberUtils.lowInt(l);
        Assertions.assertEquals(Integer.MAX_VALUE, low);
    }

    @Test
    public void testConcatLong() {
        int high;
        int low;
        long l;

        high = 0;
        low = 0;
        l = NumberUtils.concatLong(high, low);
        Assertions.assertEquals(0, l);

        high = 1;
        low = Integer.MAX_VALUE;
        l = NumberUtils.concatLong(high, low);
        Assertions.assertEquals(0x000000017FFFFFFFL, l);
    }
}
