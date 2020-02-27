package com.github.xianzhan.jvmjava.java.classpath;

import com.github.xianzhan.jvmjava.java.util.Strings;

/**
 * 通配符(*)入口
 *
 * @author xianzhan
 * @since 2020-02-27
 */
public class WildcardEntry extends Entry {

    private CompositeEntry compositeEntry;

    public WildcardEntry(String path) {
        super(path);
        // remove '*'
        String baseDir = path.substring(0, Strings.len(path) - 1);
        compositeEntry = new CompositeEntry(baseDir);
    }

    @Override
    public Entry readClass(String className) {
        return compositeEntry.readClass(className);
    }
}
