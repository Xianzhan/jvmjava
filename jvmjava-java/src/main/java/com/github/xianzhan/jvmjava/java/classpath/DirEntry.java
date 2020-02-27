package com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.util.Filepath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 目录入口
 *
 * @author xianzhan
 * @since 2020-02-27
 */
public class DirEntry extends Entry {

    private String absDir;

    public DirEntry(String path) {
        super(path);
        this.absDir = Filepath.abs(path);
    }

    @Override
    public Entry readClass(String className) {
        String fileName = Filepath.join(absDir, className);
        try {
            byte[] bytes = Files.readAllBytes(Path.of(fileName));
            this.setBytes(bytes);
        } catch (IOException e) {
            this.setError(e);
        }
        return this;
    }

    @Override
    public String toString() {
        return absDir;
    }
}
