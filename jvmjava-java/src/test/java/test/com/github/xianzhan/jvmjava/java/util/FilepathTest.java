package test.com.github.xianzhan.jvmjava.java.util;

import com.github.xianzhan.jvmjava.java.util.Filepath;
import org.junit.jupiter.api.Test;

/**
 * 文件路径测试类
 *
 * @author xianzhan
 * @since 2020-02-27
 */
public class FilepathTest {

    @Test
    public void testAbs() {
        System.out.println(Filepath.abs("."));
    }
}
