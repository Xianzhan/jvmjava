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

    @Test
    public void testInterpretOneToHundred() {
        Main.main(
                "-cp", "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/",
                "OneToHundred"
        );
    }

    /**
     * package lxz;
     *
     * public class SixObj {
     *
     *     public static int staticVar;
     *     public int instanceVar;
     *
     *     public static void main(String[] args) {
     *         int x = 32768;               // ldc
     *         SixObj obj = new SixObj();   // new
     *         SixObj.staticVar = x;        // putstatic
     *         x = SixObj.staticVar;        // getstatic
     *         obj.instanceVar = x;         // putfield
     *         x = obj.instanceVar;         // getfield
     *         Object o = obj;
     *         if (o instanceof SixObj) {   // instanceof
     *             obj = (SixObj) o;        // checkcast
     *             System.out.println(obj.instanceVar);
     *         }
     *     }
     * }
     */
    @Test
    public void testInterpretSixObj() {
        Main.main(
                "-cp", "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/",
                "SixObj"
        );
    }
}
