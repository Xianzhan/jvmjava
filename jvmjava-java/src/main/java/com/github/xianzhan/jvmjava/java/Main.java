package com.github.xianzhan.jvmjava.java;

import com.github.xianzhan.jvmjava.java.cmd.Argument;
import com.github.xianzhan.jvmjava.java.flag.Flag;

import java.util.Arrays;

/**
 * java [-options] class [args]         <br>
 * java [-options] -jar jarfile [args]  <br>
 * javaw [-options] class [args]        <br>
 * javaw [-options] -jar jarfile [args] <br>
 *
 * <table>
 *     <tr><td>选项</td><td>用途</td></tr>
 *     <tr><td><code>-version</code></td><td>输出版本信息, 然后退出</td></tr>
 *     <tr><td><code>-?/-help</code></td><td>输出帮助信息, 然后退出</td></tr>
 *     <tr><td><code>-cp/-classpath</code></td><td>指定用户类路径</td></tr>
 *     <tr><td><code>-Dproperty=value</code></td><td>设置 Java 系统属性</td></tr>
 *     <tr><td><code>-Xms[size]</code></td><td>设置初始堆空间大小</td></tr>
 *     <tr><td><code>-Xmx[size]</code></td><td>设置最大堆空间大小</td></tr>
 *     <tr><td><code>-Xss[size]</code></td><td>设置线程栈空间大小</td></tr>
 * </table>
 *
 * @author xianzhan
 * @since 2020-02-26
 */
public class Main {

    public static void main(String... args) {
        Argument cmd = parseCmd(args);
        if (cmd.isVersionFlag()) {
            System.out.println("version 0.0.1");
        } else if (cmd.isHelpFlag() || cmd.getClazz().isEmpty()) {
            printUsage();
        } else {
            startJVM(cmd);
        }
    }

    private static Argument parseCmd(String[] args) {
        Argument cmd = new Argument();

        Flag flag = new Flag(args);
        flag.boolVar(cmd::setHelpFlag, "-help", false, "print help message");
        flag.boolVar(cmd::setHelpFlag, "-?", false, "print help message");
        flag.boolVar(cmd::setVersionFlag, "-version", false, "print version and exit");
        flag.stringVar(cmd::setCpOption, "-classpath", "", "classpath");
        flag.stringVar(cmd::setCpOption, "-cp", "", "classpath");
        flag.parse();
        args = flag.args();
        if (args.length > 0) {
            cmd.setClazz(args[0]);
            cmd.setArgs(Arrays.copyOfRange(args, 1, args.length));
        }
        return cmd;
    }

    private static void startJVM(Argument cmd) {
        System.out.printf("classpath: %s class: %s args: %s",
                cmd.getCpOption(),
                cmd.getClazz(),
                Arrays.toString(cmd.getArgs()));
    }

    private static void printUsage() {
        System.out.println("Usage: java [-options] class [args...]");
    }
}
