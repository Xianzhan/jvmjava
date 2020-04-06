package test.com.github.xianzhan.jvmjava.java;

import com.github.xianzhan.jvmjava.java.Main;
import org.junit.jupiter.api.Test;

/**
 * test
 *
 * @author xianzhan
 * @since 2020-02-26
 */
public class MainTest {

    @Test
    public void testHelp() {
        Main.main("-help");
    }

    @Test
    public void testVersion() {
        Main.main("-version");
    }
}
