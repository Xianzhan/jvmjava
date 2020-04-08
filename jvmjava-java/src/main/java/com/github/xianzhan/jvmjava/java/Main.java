package com.github.xianzhan.jvmjava.java;

import com.github.xianzhan.jvmjava.java.cmd.Args;

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
            System.out.println(String.format("java version %s", "8"));
            return;
        }

        launch(cmd);
    }

    private static void launch(Args cmd) {

    }
}
