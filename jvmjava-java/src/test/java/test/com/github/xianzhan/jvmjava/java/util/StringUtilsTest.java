package test.com.github.xianzhan.jvmjava.java.util;

import com.github.xianzhan.jvmjava.java.util.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author xianzhan
 * @since 2020-04-08
 */
public class StringUtilsTest {

    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(StringUtils.isEmpty(null));
        Assertions.assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void testStartsWith() {
        Assertions.assertTrue(StringUtils.startsWith("abc", "ab"));
    }
}
