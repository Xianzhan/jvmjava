package com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.classfile.ClassFile;
import com.github.xianzhan.jvmjava.java.classfile.ClassReader;
import com.github.xianzhan.jvmjava.java.util.ArrayUtils;
import com.github.xianzhan.jvmjava.java.util.CollectionUtils;
import com.github.xianzhan.jvmjava.java.util.PathUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * 目录 Entry
 * 检测目录下的 class 文件和 jar 文件
 *
 * @author xianzhan
 * @since 2020-04-09
 */
class DirEntry implements Entry {

    /**
     * 是否已经检测过 jar 文件
     */
    private boolean     testJar;
    private List<Entry> compressEntryList;

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

        if (testJar && CollectionUtils.isEmpty(compressEntryList)) {
            return null;
        }

        test(absPath);
        return readFromJar(classname);
    }

    private ClassFile readFromJar(String classname) {
        if (CollectionUtils.isEmpty(compressEntryList)) {
            return null;
        }

        for (Entry entry : compressEntryList) {
            ClassFile classFile = entry.readClass(classname);
            if (classFile != null) {
                return classFile;
            }
        }
        return null;
    }

    private void test(String dir) {
        if (testJar) {
            return;
        }
        testJar = true;

        String[] fileList = Path.of(dir)
                .toFile()
                .list();
        if (ArrayUtils.isEmpty(fileList)) {
            return;
        }

        if (compressEntryList == null) {
            for (var file : fileList) {
                if (PathUtils.isCompressedName(file)) {
                    String allPathFile = PathUtils.join(dirPath, file);
                    if (compressEntryList == null) {
                        compressEntryList = CollectionUtils.newArrayList();
                    }
                    compressEntryList.add(Entry.newEntry(allPathFile));
                }
            }
        }
    }
}
