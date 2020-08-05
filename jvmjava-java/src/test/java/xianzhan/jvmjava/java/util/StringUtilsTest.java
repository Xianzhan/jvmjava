package xianzhan.jvmjava.java.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author xianzhan
 * @since 2020-04-08
 */
public class StringUtilsTest {

    @Test
    public void testContains() {
        Assertions.assertTrue(StringUtils.contains("abc", "b"));
        Assertions.assertFalse(StringUtils.contains("abc", "d"));
    }

    @Test
    public void testEndsWith() {
        Assertions.assertTrue(StringUtils.endsWith("abc", "c"));
        Assertions.assertFalse(StringUtils.endsWith("abc", "b"));
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(StringUtils.isEmpty(null));
        Assertions.assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void testSplit() {
        String str = ";;12";
        Assertions.assertEquals(StringUtils.split(str, ";").length, 3);
        str = "12;12;22";
        Assertions.assertEquals(StringUtils.split(str, ";").length, 3);
        str = "12;";
        Assertions.assertEquals(StringUtils.split(str, ";").length, 2);
        str = null;
        Assertions.assertEquals(StringUtils.split(str, ";").length, 0);
    }

    @Test
    public void testStartsWith() {
        Assertions.assertTrue(StringUtils.startsWith("abc", "ab"));
    }
}
