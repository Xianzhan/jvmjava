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
     *
     * ====== Bytecode ======
     *  0 iconst_0
     *  1 istore_1
     *  2 iconst_0
     *  3 istore_2
     *  4 iload_2
     *  5 bipush 100
     *  7 if_icmpgt 20 (+13)
     * 10 iload_1
     * 11 iload_2
     * 12 iadd
     * 13 istore_1
     * 14 iinc 2 by 1
     * 17 goto 4 (-13)
     * 20 getstatic #7 <java/lang/System.out>
     * 23 iload_1
     * 24 invokevirtual #13 <java/io/PrintStream.println>
     * 27 return
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
//                interpreter.interpret(method);
                break;
            }
        }
    }
}
