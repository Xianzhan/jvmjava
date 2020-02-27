package test.com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.classpath.ZipEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author xianzhan
 * @since 2020-02-27
 */
public class ZipEntryTest {

    @Test
    public void testReadClass() {
        ZipEntry zipEntry = new ZipEntry("src\\test\\resources\\test\\com\\github\\xianzhan\\jvmjava\\java\\classpath\\abc.zip");
        zipEntry.readClass("abc.txt");
        String abc = new String(zipEntry.getBytes());
        Assertions.assertEquals(abc, "abc");
    }
}
