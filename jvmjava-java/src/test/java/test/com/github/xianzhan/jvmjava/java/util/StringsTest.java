package test.com.github.xianzhan.jvmjava.java.util;

import com.github.xianzhan.jvmjava.java.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 字符串工具类测试类
 *
 * @author xianzhan
 * @since 2020-02-27
 */
public class StringsTest {

    @Test
    public void testLen() {
        Assertions.assertEquals(Strings.len(null), 0);
        Assertions.assertEquals(Strings.len(""), 0);
        Assertions.assertEquals(Strings.len("1"), 1);
        Assertions.assertEquals(Strings.len("22"), 2);
    }

    @Test
    public void testHasSuffix() {
        Assertions.assertTrue(Strings.hasSuffix("*.jar", "jar"));
        Assertions.assertFalse(Strings.hasSuffix("*.jar", "zip"));
    }

    @Test
    public void testSplit() {
        System.out.println(Arrays.toString(Strings.split("a\\b", "\\\\")));
    }
}
