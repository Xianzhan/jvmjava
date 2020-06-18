package test.com.github.xianzhan.jvmjava.java.interpret;

import com.github.xianzhan.jvmjava.java.classfile.ClassFile;
import com.github.xianzhan.jvmjava.java.classfile.ClassReader;
import com.github.xianzhan.jvmjava.java.interpret.Interpreter;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author xianzhan
 * @since 2020-06-18
 */
public class InterpreterTest {

    /**
     * public class OneToHundred {
     *     public static void main(String[] args) {
     *         int sum = 0;
     *         for (int i = 0; i <= 100; i++) {
     *             sum += i;
     *         }
     *         System.out.println(sum);
     *     }
     * }
     */
    @Test
    public void testInterpret() throws IOException {
        String file = "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/OneToHundred.class";
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        ClassFile classFile = ClassReader.read(dis);

        var methods = classFile.methods;
        for (var method : methods.methods) {
            if ("main".equals(method.name)) {
                Interpreter interpreter = new Interpreter();
                interpreter.interpret(method);
                break;
            }
        }
    }
}
