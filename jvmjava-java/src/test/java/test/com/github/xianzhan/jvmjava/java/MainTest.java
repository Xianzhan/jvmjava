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
    public void main01() {
        Main.main("-cp", ".", "com.github.xianzhan.jvmjava.java.Main", "arg1", "arg2");
    }

    @Test
    public void main02() {
        Main.main("-cp", "src\\test\\resources\\test\\com\\github\\xianzhan\\jvmjava\\java", "java.lang.Object");
    }
}
