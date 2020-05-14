package com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.classfile.ClassFile;
import com.github.xianzhan.jvmjava.java.classfile.ClassReader;
import com.github.xianzhan.jvmjava.java.util.PathUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.ZipFile;

/**
 * xx.jar/xx.zip
 *
 * @author xianzhan
 * @since 2020-04-09
 */
class ZipEntry implements Entry {

    private final String path;

    public ZipEntry(String path) {
        this.path = path;
    }

    @Override
    public ClassFile readClass(String classname) {
        String absPath = PathUtils.absolute(path);
        try (ZipFile zipFile = new ZipFile(absPath)) {
            String classfilePath = PathUtils.toClassfilePath(classname);
            var entry = zipFile.getEntry(classfilePath + PathUtils.DOT_CLASS);
            if (entry == null) {
                return null;
            }

            try (var is = zipFile.getInputStream(entry)) {
                return ClassReader.read(new DataInputStream(is));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("classname: " + classname);
    }
}
