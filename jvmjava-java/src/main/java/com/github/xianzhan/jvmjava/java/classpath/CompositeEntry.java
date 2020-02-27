package com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 混合文件入口
 *
 * @author xianzhan
 * @since 2020-02-27
 */
public class CompositeEntry extends Entry {

    private List<Entry> entryList;

    public CompositeEntry(String pathList) {
        super(pathList);
        entryList = new ArrayList<>();
        String[] paths = Strings.split(pathList, PATH_LIST_SEPARATOR);
        for (String path : paths) {
            var entry = Entry.newEntry(path);
            this.entryList.add(entry);
        }
    }

    @Override
    public Entry readClass(String className) {
        for (Entry entry : entryList) {
            Entry read = entry.readClass(className);
            if (read.getError() == null) {
                return read;
            }
        }
        this.setError(new RuntimeException(className + " not found"));
        return this;
    }

    @Override
    public String toString() {
        return entryList.stream()
                .map(Object::toString)
                .collect(Collectors.joining(PATH_LIST_SEPARATOR));
    }
}
