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
     * public class EightHello {
     *     public static void main(String[] args) {
     *         System.out.println("Hello world");
     *     }
     * }
     */
    @Test
    public void testInterpretEightHello() {
        Main.main(
                "-cp", "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/",
                "EightHello"
        );
    }

    /**
     * package lxz;
     *
     * public class EightArgs {
     *     public static void main(String[] args) {
     *         for (String arg : args) {
     *             System.out.println(arg);
     *         }
     *     }
     * }
     */
    @Test
    public void testInterpretEightArgs() {
        Main.main(
                "-cp", "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/",
//                "-verbose:class",
                "EightArgs",
                "123", "你好, 世界"
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

    /**
     * package lxz;
     *
     * public class NineGetClass {
     *     public static void main(String[] args) {
     *         System.out.println(void.class.getName());               // void
     *         System.out.println(boolean.class.getName());            // boolean
     *         System.out.println(byte.class.getName());               // byte
     *         System.out.println(char.class.getName());               // char
     *         System.out.println(short.class.getName());              // short
     *         System.out.println(int.class.getName());                // int
     *         System.out.println(long.class.getName());               // long
     *         System.out.println(float.class.getName());              // float
     *         System.out.println(double.class.getName());             // double
     *         System.out.println(Object.class.getName());             // java.lang.Object
     *         System.out.println(int[].class.getName());              // [I
     *         System.out.println(int[][].class.getName());            // [[I
     *         System.out.println(Object[].class.getName());           // [Ljava.lang.Object;
     *         System.out.println(Object[][].class.getName());         // [[Ljava.lang.Object;
     *         System.out.println(Runnable.class.getName());           // java.lang.Runnable
     *         System.out.println("abc".getClass().getName());         // java.lang.String
     *         System.out.println(new double[0].getClass().getName()); // [D
     *         System.out.println(new String[0].getClass().getName()); // [Ljava.lang.String;
     *     }
     * }
     */
    @Test
    public void testInterpretNineGetClass() {
        Main.main(
                "-cp", "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/",
//                "-verbose:class",
//                "-verbose:inst",
                "NineGetClass"
        );
    }

    /**
     * package lxz;
     *
     * public class NineString {
     *     public static void main(String[] args) {
     *         String s1 = "abc1";
     *         String s2 = "abc1";
     *         System.out.println(s1 == s2); // true
     *         int x = 1;
     *         String s3 = "abc" + x;
     *         System.out.println(s1 == s3); // false
     *         s3 = s3.intern();
     *         System.out.println(s1 == s3); // true
     *     }
     * }
     */
    @Test
    public void testInterpretNineString() {
        Main.main(
                "-cp", "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/",
//                "-verbose:class",
//                "-verbose:inst",
                "NineString"
        );
    }

    /**
     * package lxz;
     *
     * public class TenParseInt {
     *     public static void main(String[] args) {
     *         foo(args);
     *     }
     *
     *     private static void foo(String[] args) {
     *         try {
     *             bar(args);
     *         } catch (NumberFormatException e) {
     *             System.out.println(e.getMessage());
     *         }
     *     }
     *
     *     private static void bar(String[] args) {
     *         if (args.length == 0) {
     *             throw new IndexOutOfBoundsException("no args!");
     *         }
     *         int x = Integer.parseInt(args[0]);
     *         System.out.println(x);
     *     }
     * }
     */
    @Test
    public void testInterpretTenParseInt() {
        Main.main(
                "-cp", "src/test/resources/test/com/github/xianzhan/jvmjava/java/interpret/",
//                "-verbose:class",
//                "-verbose:inst",
                "TenParseInt"
//                , "123"
        );
    }
}
