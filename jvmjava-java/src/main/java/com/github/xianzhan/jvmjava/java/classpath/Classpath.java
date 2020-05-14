package com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.classfile.ClassFile;
import com.github.xianzhan.jvmjava.java.util.PathUtils;
import com.github.xianzhan.jvmjava.java.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author xianzhan
 * @since 2020-04-19
 */
public class Classpath {

    private Entry bootClasspath;
    private Entry extClasspath;
    private Entry userClasspath;

    public ClassFile readClass(String classname) {
        // Parent delegation model

        ClassFile bootClassFile = bootClasspath.readClass(classname);
        if (bootClassFile != null) {
            return bootClassFile;
        }

        ClassFile extClassFile = extClasspath.readClass(classname);
        if (extClassFile != null) {
            return extClassFile;
        }

        return userClasspath.readClass(classname);
    }

    public static Classpath parse(String jreOption, String cpOption) {
        Classpath cp = new Classpath();
        cp.parseBootAntExtClasspath(jreOption);
        cp.parseUserClasspath(cpOption);
        return cp;
    }


    private void parseBootAntExtClasspath(String jreOption) {
        var jreDir = getJreDir(jreOption);

        var jreLibPath = PathUtils.join(jreDir, "lib", "*");
        bootClasspath = Entry.newEntry(jreLibPath);

        var jreExtPath = PathUtils.join(jreDir, "lib", "ext", "*");
        extClasspath = Entry.newEntry(jreExtPath);
    }

    private void parseUserClasspath(String cpOption) {
        if (StringUtils.isEmpty(cpOption)) {
            cpOption = ".";
        }
        userClasspath = Entry.newEntry(cpOption);
    }

    private String getJreDir(String jreOption) {
        if (!StringUtils.isEmpty(jreOption) && Files.exists(Path.of(jreOption))) {
            return jreOption;
        }

        var jh = System.getenv("JAVA8_HOME");
        if (StringUtils.isEmpty(jh)) {
            throw new RuntimeException("Can not find jre folder!");
        }
        return PathUtils.join(jh, "jre");
    }
}
