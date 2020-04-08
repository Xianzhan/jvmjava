package test.com.github.xianzhan.jvmjava.java.util;

import com.github.xianzhan.jvmjava.java.util.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Array tools test
 *
 * @author xianzhan
 * @since 2020-04-06
 */
public class ArrayUtilsTest {

    private static String[] arr;

    @BeforeAll
    public static void before() {
        arr = new String[]{"a", "b", "c"};
    }

    @Test
    public void testAnyContains() {
        Assertions.assertTrue(ArrayUtils.anyContains(arr, "B", "b"));
    }

    @Test
    public void testIndexOf() {
        Assertions.assertEquals(ArrayUtils.indexOf(arr, "a"), 0);
        Assertions.assertEquals(ArrayUtils.indexOf(arr, "z"), -1);
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(ArrayUtils.isEmpty(null));
        Assertions.assertTrue(ArrayUtils.isEmpty(new Object[0]));
        Assertions.assertTrue(ArrayUtils.isEmpty(new String[0]));
    }
}
