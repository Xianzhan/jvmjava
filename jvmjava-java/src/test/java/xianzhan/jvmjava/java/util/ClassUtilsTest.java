package xianzhan.jvmjava.java.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author xianzhan
 * @since 2020-07-04
 */
public class ClassUtilsTest {

    @Test
    public void testGetComponentClassName() {
        Assertions.assertEquals("[xxx", ClassUtils.getComponentClassName("[[xxx"));
        Assertions.assertEquals("xx", ClassUtils.getComponentClassName("[Lxx;"));
        Assertions.assertEquals("int", ClassUtils.getComponentClassName("[I"));
    }
}
