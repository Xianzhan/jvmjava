package com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.util.Filepath;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipFile;

/**
 * zip 压缩包
 *
 * @author xianzhan
 * @since 2020-02-27
 */
public class ZipEntry extends Entry {

    private String absPath;

    public ZipEntry(String path) {
        super(path);
        this.absPath = Filepath.abs(path);
    }

    @Override
    public Entry readClass(String className) {
        try (ZipFile zipFile = new ZipFile(absPath)) {
            var classEntry = zipFile.getEntry(className);
            InputStream inputStream = zipFile.getInputStream(classEntry);
            byte[] bytes = inputStream.readAllBytes();
            this.setBytes(bytes);
        } catch (IOException e) {
            this.setError(e);
        }
        return this;
    }

    @Override
    public String toString() {
        return absPath;
    }
}
