package com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.util.Filepath;
import com.github.xianzhan.jvmjava.java.util.Strings;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * classpath
 *
 * @author xianzhan
 * @since 2020-02-27
 */
public class Classpath {

    private Entry bootClasspath;
    private Entry extClasspath;
    private Entry userClasspath;

    public Entry readClass(String className) {
        className = className + ".class";
        Entry entry = bootClasspath.readClass(className);
        if (entry.getError() == null) {
            return entry;
        }
        entry = extClasspath.readClass(className);
        if (entry.getError() == null) {
            return entry;
        }
        return userClasspath.readClass(className);
    }

    public static Classpath parse(String jreOption, String cpOption) {
        Classpath cp = new Classpath();
        cp.parseBootAndExtClasspath(jreOption);
        cp.parseUserClasspath(cpOption);
        return cp;
    }

    private void parseUserClasspath(String cpOption) {
        if (Strings.isEmpty(cpOption)) {
            cpOption = ".";
        }
        this.userClasspath = Entry.newEntry(cpOption);
    }

    private void parseBootAndExtClasspath(String jreOption) {
        var jreDir = getJreDir(jreOption);

        var jreLibPath = Filepath.join(jreDir, "lib", "*");
        this.bootClasspath = new WildcardEntry(jreLibPath);

        var jreExtPath = Filepath.join(jreDir, "lib", "ext", "*");
        this.extClasspath = new WildcardEntry(jreExtPath);
    }

    private String getJreDir(String jreOption) {
        if (!jreOption.isEmpty() && exists(jreOption)) {
            return jreOption;
        }
        if (exists("./jre")) {
            return "./jre";
        }
        String jh;
        if ((jh = System.getenv("JAVA_HOME")) != null) {
            return Filepath.join(jh, "jre");
        }
        throw new RuntimeException("Can not find jre folder!");
    }

    private boolean exists(String path) {
        return Files.exists(Path.of(path));
    }

    @Override
    public String toString() {
        return userClasspath.toString();
    }
}
