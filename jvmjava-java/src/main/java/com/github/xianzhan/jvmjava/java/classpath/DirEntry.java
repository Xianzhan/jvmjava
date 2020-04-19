package com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.classfile.ClassFile;
import com.github.xianzhan.jvmjava.java.classfile.ClassReader;
import com.github.xianzhan.jvmjava.java.util.PathUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author xianzhan
 * @since 2020-04-09
 */
class DirEntry implements Entry {

    private final String dirPath;

    public DirEntry(String path) {
        this.dirPath = path;
    }

    @Override
    public ClassFile readClass(String classname) {
        String absPath = PathUtils.absolute(dirPath);
        String classfile = PathUtils.toClassfilePath(classname) + PathUtils.DOT_CLASS;
        Path p = Path.of(absPath, classfile);
        if (Files.exists(p)) {
            try (var is = Files.newInputStream(p)) {
                return ClassReader.read(new DataInputStream(is));
            } catch (IOException e) {
                throw new IllegalArgumentException(classname, e);
            }
        }
        return null;
    }
}
