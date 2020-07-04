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

    /**
     * package lxz;
     *
     * public class SevenInvoke implements Runnable {
     *     public static void main(String[] args) {
     *         new SevenInvoke().test();
     *     }
     *
     *     public void test() {
     *         SevenInvoke.staticMethod();           // invokestatic
     *         SevenInvoke demo = new SevenInvoke(); // invokespecial
     *         demo.instanceMethod();                // invokespecial
     *         super.equals(null);                   // invokespecial
     *         this.run();                           // invokevirtual
     *         ((Runnable) demo).run();              // invokeinterface
     *     }
     *
     *     public static void staticMethod() {
     *     }
     *
     *     private void instanceMethod() {
     *     }
     *
     *     @Override
     *     public void run() {
     *     }
     * }
     */
    @Test
    public void testInterpretSevenInvoke() {
        Main.main(
                "-cp", "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/",
                "SevenInvoke"
        );
    }

    /**
     * package lxz;
     *
     * public class SevenFibonacci {
     *     public static void main(String[] args) {
     *         long x = fibonacci(30);
     *         System.out.println(x);
     *     }
     *
     *     private static long fibonacci(long n) {
     *         if (n <= 1) {
     *             return n;
     *         }
     *         return fibonacci(n - 1) + fibonacci(n - 2);
     *     }
     * }
     */
    @Test
    public void testInterpretSevenFibonacci() {
        Main.main(
                "-cp", "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/",
                "SevenFibonacci"
        );
    }

    /**
     * package lxz;
     *
     * public class EightBubbleSort {
     *     public static void main(String[] args) {
     *         int[] arr = { 22, 84, 77, 11, 95, 9, 78, 56, 36, 97, 65, 36, 10, 24, 92, 48 };
     *         bubbleSort(arr);
     *         printArray(arr);
     *     }
     *
     *     private static void bubbleSort(int[] arr) {
     *         boolean swapped = true;
     *         int j = 0;
     *         int tmp;
     *         while (swapped) {
     *             swapped = false;
     *             j++;
     *             for (int i = 0; i < arr.length - j; i++) {
     *                 if (arr[i] > arr[i + 1]) {
     *                     tmp = arr[i];
     *                     arr[i] = arr[i + 1];
     *                     arr[i + 1] = tmp;
     *                     swapped = true;
     *                 }
     *             }
     *         }
     *     }
     *
     *     private static void printArray(int[] arr) {
     *         for (int i : arr) {
     *             System.out.println(i);
     *         }
     *     }
     * }
     */
    @Test
    public void testInterpretEightBubbleSort() {
        Main.main(
                "-cp", "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/",
                "EightBubbleSort"
        );
    }

    /**
     * package lxz;
     *
     * public class EightArray {
     *
     *     public static void main(String[] args) {
     *         int[] a1 = new int[10];       // newarray
     *         String[] a2 = new String[10]; // anewarray
     *         int[][] a3 = new int[10][10]; // multianewarray
     *         int x = a1.length;            // arraylength
     *         a1[0] = 100;                  // iastore
     *         int y = a1[0];                // iaload
     *         a2[0] = "abc";                // aastore
     *         String s = a2[0];             // aaload
     *     }
     * }
     */
    @Test
    public void testInterpretEightArray() {
        Main.main(
                "-cp", "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/",
                "EightArray"
        );
    }
}
