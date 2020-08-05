package xianzhan.jvmjava.java.classpath;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xianzhan.jvmjava.java.classfile.ClassFile;

/**
 * @author xianzhan
 * @since 2020-05-13
 */
public class ClasspathTest {

    @Test
    public void testParse() {
        var jre = "";
        var cp = "";
        Classpath classpath = Classpath.parse(jre, cp);
        ClassFile classFile = classpath.readClass("java.lang.Object");
        Assertions.assertNotNull(classFile);
    }
}
