package test.com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.classpath.Entry;
import com.github.xianzhan.jvmjava.java.util.PathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author xianzhan
 * @since 2020-04-11
 */
public class EntryTest {

    @Test
    public void testCompositeEntry() {
        String path = "src/test/resources/test/com/github/xianzhan/jvmjava/java/classpath/abc.zip";
        path += PathUtils.PATH_SEPARATOR;
        path += "src/test/resources/test/com/github/xianzhan/jvmjava/java/java/lang/Object.class";
        Entry entry = Entry.newEntry(path);
        Assertions.assertNotNull(entry.readClass("java.lang.Object"));
    }

    @Test
    public void testDirEntry() {
        String path = "src/test/resources/test/com/github/xianzhan/jvmjava/java";
        Entry entry = Entry.newEntry(path);
        Assertions.assertNotNull(entry.readClass("java.lang.Object"));
    }

    @Test
    public void testWildcardEntry() {
        String path = "src/test/resources/test/com/github/xianzhan/jvmjava/java/*";
        Entry entry = Entry.newEntry(path);
        Assertions.assertNotNull(entry.readClass("java.lang.Object"));
    }

    @Test
    public void testZipEntry() {
        Entry entry = Entry.newEntry("src/test/resources/test/com/github/xianzhan/jvmjava/java/classpath/abc.zip");
        Assertions.assertNotNull(entry.readClass("java.lang.Object"));
    }
}
