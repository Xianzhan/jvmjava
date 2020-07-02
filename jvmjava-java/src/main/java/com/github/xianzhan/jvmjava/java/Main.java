package com.github.xianzhan.jvmjava.java;

import com.github.xianzhan.jvmjava.java.classloader.ClassLoader;
import com.github.xianzhan.jvmjava.java.classpath.Classpath;
import com.github.xianzhan.jvmjava.java.cmd.Args;
import com.github.xianzhan.jvmjava.java.interpret.Interpreter;
import com.github.xianzhan.jvmjava.java.util.PathUtils;

/**
 * java application impl
 *
 * @author xianzhan
 * @since 2020-02-26
 */
public class Main {

    public static void main(String... args) {
        Args cmd = Args.parseArgs(args);
        if (cmd.isHelp()) {
            System.out.println(Args.helpInformation());
            return;
        }

        if (cmd.isVersion()) {
            System.out.println("java version %i".formatted(8));
            return;
        }

        launch(cmd);
    }

    private static void launch(Args cmd) {
        var cp = Classpath.parse(cmd.xJreOption(), cmd.getClasspath());
        var classLoader = new ClassLoader(cp, cmd.isVerboseClassFlag());

        var mainClassName = cmd.getMainClass();
        var className = PathUtils.toClassfilePath(mainClassName);
        var mainClass = classLoader.loadClass(className);
        var mainMethod = mainClass.getMainMethod();
        if (mainMethod != null) {
            var interpreter = new Interpreter();
            interpreter.interpret(mainMethod, cmd.isVerboseInstFlag());
        } else {
            System.err.println("Main method not found in class %s\n".formatted(mainClassName));
        }
    }
}
