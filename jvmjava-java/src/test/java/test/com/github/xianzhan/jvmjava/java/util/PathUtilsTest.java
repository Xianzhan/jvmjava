package test.com.github.xianzhan.jvmjava.java.util;

import com.github.xianzhan.jvmjava.java.util.PathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author xianzhan
 * @since 2020-04-10
 */
public class PathUtilsTest {

    @Test
    public void testIsCompressedName() {
        Assertions.assertTrue(PathUtils.isCompressedName("xxx.jar"));
        Assertions.assertTrue(PathUtils.isCompressedName("xxx.zip"));
        Assertions.assertFalse(PathUtils.isCompressedName("xxx.txt"));
    }
}
