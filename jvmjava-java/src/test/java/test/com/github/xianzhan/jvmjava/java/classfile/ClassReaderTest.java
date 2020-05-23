package test.com.github.xianzhan.jvmjava.java.classfile;

import com.github.xianzhan.jvmjava.java.classfile.ClassFile;
import com.github.xianzhan.jvmjava.java.classfile.ClassReader;
import com.github.xianzhan.jvmjava.java.classpath.Classpath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author xianzhan
 * @since 2020-05-20
 */
public class ClassReaderTest {

    @Test
    public void testRead() throws IOException {
        String file = "src/test/resources/test/com/github/xianzhan/jvmjava/java/java/lang/String.class";
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        ClassFile classFile = ClassReader.read(dis);

        Assertions.assertNotNull(classFile, "不能读取字节码文件");
        Assertions.assertEquals(classFile.magic, 0xCAFEBABE);
    }

    @Test
    public void testReadByteCode() {
        Classpath classpath = Classpath.parse(null, null);
        ClassFile classFile = classpath.readClass("java.lang.String");

        Assertions.assertNotNull(classFile, "不能读取字符串类");
        classFile.printInfo();
    }
}
