package com.github.xianzhan.jvmjava.java.cmd;

/**
 * Command line Arguments
 *
 * @author xianzhan
 * @since 2020-02-26
 */
public interface Args {

    /**
     * Check if it is show version command.
     *
     * @return true is version command.
     */
    boolean isVersion();

    /**
     * Check if it is show help command.
     *
     * @return true is help command.
     */
    boolean isHelp();

    /**
     * Get class path
     *
     * @return class path
     */
    String getClasspath();

    String getMainClass();

    /**
     * Parse args into Args instances.
     *
     * @param args common line args
     * @return Args instance
     */
    static Args parseArgs(String... args) {
        return CommonLine.parseArgs(args);
    }

    static String helpInformation() {
        return "Usage: java [-options] class [args...]\n" +
               "    or java [-options] -jar jarfile [args...]\n" +
               "\n" +
               "options:\n" +
               "       -help    Print this help\n" +
               "       -version Print version\n";
    }
}
