package com.github.xianzhan.jvmjava.java.cmd;

import com.github.xianzhan.jvmjava.java.util.ArrayUtils;
import com.github.xianzhan.jvmjava.java.util.PathUtils;
import com.github.xianzhan.jvmjava.java.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * Common Line Class
 *
 * @author xianzhan
 * @since 2020-04-06
 */
class CommonLine {

    /**
     * Limit the number of parameters
     */
    private static final int LIMIT_ARGS_COUNT = 200;

    private static final String HYPHEN         = "-";
    private static final String HYPHEN_VERSION = "-version";
    private static final String HYPHEN_HELP    = "-help";
    private static final String HYPHEN_CP      = "-cp";
    private static final String HYPHEN_JAR     = "-jar";

    public static Args parseArgs(String... cliArgs) {
        if (cliArgs.length > LIMIT_ARGS_COUNT) {
            throw new IllegalArgumentException("Too many Args");
        }

        ArgsImpl ret = new ArgsImpl(cliArgs);
        if (ArrayUtils.isEmpty(cliArgs)) {
            ret.setHelp(true);
            return ret;
        }
        if (ArrayUtils.anyContains(cliArgs, HYPHEN_VERSION)) {
            // -version
            ret.setVersion(true);
            return ret;
        }
        if (ArrayUtils.anyContains(cliArgs, HYPHEN_HELP)) {
            // -help
            ret.setHelp(true);
            return ret;
        }

        // Parse arguments
        final int length = cliArgs.length;
        int index = 0;
        while (index < length) {
            String arg = cliArgs[index];
            if (!StringUtils.startsWith(arg, HYPHEN)) {
                break;
            }

            if (HYPHEN_CP.equals(arg)) {
                int classpathIndex = ++index;
                if (classpathIndex >= length) {
                    throw new IllegalArgumentException("Miss classpath");
                }
                String classpath = cliArgs[classpathIndex];
                ret.setClasspath(classpath);
            } else if (HYPHEN_JAR.equals(arg)) {
                int mainJarIndex = ++index;
                if (mainJarIndex >= length) {
                    throw new IllegalArgumentException("Miss main jar");
                }
                String mainJar = cliArgs[mainJarIndex];
                ret.appendClasspath(mainJar);
                String mainClass = parseMainClass(mainJar);
                ret.setMainClass(mainClass);
            }

            index++;
        }

        // parse main class
        if (ret.getMainClass() == null) {
            if (index >= length) {
                throw new IllegalArgumentException("miss main class");
            }
            String mainClass = cliArgs[index++];
            ret.setMainClass(mainClass);
        }
        // Main class args
        if (index < length) {
            int argsLength = length - index;
            String[] args = new String[argsLength];
            System.arraycopy(cliArgs, index, args, 0, argsLength);
            ret.setArgs(args);
        }

        return ret;
    }

    private static String parseMainClass(String mainJar) {
        try (JarFile jarFile = new JarFile(mainJar)) {
            ZipEntry entry = jarFile.getEntry("META-INF/MANIFEST.MF");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(jarFile.getInputStream(entry)))) {
                String mainClass = "Main-Class: ";
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith(mainClass)) {
                        return line.substring(mainClass.length());
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Not main jar", e);
        }
        throw new IllegalArgumentException("Not found main class, jar file: " + mainJar);
    }

    private static class ArgsImpl implements Args {

        private final String[] cliArgs;

        private boolean  version;
        private boolean  help;
        private String   classpath;
        private String   mainClass;
        private String[] args;

        public ArgsImpl(String[] cliArgs) {
            this.cliArgs = cliArgs;
        }

        public void appendClasspath(String jar) {
            String classpath = getClasspath();
            classpath += PathUtils.PATH_SEPARATOR + jar;
            setClasspath(classpath);
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
            return help;
        }

        @Override
        public String getClasspath() {
            return classpath;
        }

        public void setClasspath(String classpath) {
            this.classpath = classpath;
        }

        @Override
        public String getMainClass() {
            return mainClass;
        }

        public void setMainClass(String mainClass) {
            this.mainClass = mainClass;
        }

        public String[] getArgs() {
            return args;
        }

        public void setArgs(String[] args) {
            this.args = args;
        }

        @Override
        public String toString() {
            return Arrays.toString(cliArgs);
        }
    }
}
