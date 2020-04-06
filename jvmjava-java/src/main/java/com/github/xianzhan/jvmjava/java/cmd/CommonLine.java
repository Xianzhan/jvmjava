package com.github.xianzhan.jvmjava.java.cmd;

import com.github.xianzhan.jvmjava.java.util.ArrayUtils;

import java.util.Arrays;

/**
 * Common Line Class
 *
 * @author xianzhan
 * @since 2020-04-06
 */
class CommonLine {

    private static final String HYPHEN_VERSION = "-version";
    private static final String HYPHEN_HELP    = "-help";

    public static Args parseArgs(String... args) {
        ArgsImpl ret = new ArgsImpl(args);
        if (ArrayUtils.isEmpty(args)) {
            ret.setHelp(true);
            return ret;
        }
        if (ArrayUtils.anyContains(args, HYPHEN_VERSION)) {
            ret.setVersion(true);
            return ret;
        }
        if (ArrayUtils.anyContains(args, HYPHEN_HELP)) {
            ret.setHelp(true);
            return ret;
        }

        return ret;
    }

    private static class ArgsImpl implements Args {

        private String[] args;

        private boolean version;
        private boolean help;

        public ArgsImpl(String[] args) {
            this.args = args;
        }

        public void setVersion(boolean version) {
            this.version = version;
        }

        @Override
        public boolean isVersion() {
            return version;
        }

        public void setHelp(boolean help) {
            this.help = help;
        }

        @Override
        public boolean isHelp() {
            return ArrayUtils.isEmpty(args) || ArrayUtils.anyContains(args, "-help", "-?");
        }

        @Override
        public String toString() {
            return Arrays.toString(args);
        }
    }
}
