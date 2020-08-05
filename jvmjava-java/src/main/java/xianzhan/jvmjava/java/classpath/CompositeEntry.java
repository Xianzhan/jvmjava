package xianzhan.jvmjava.java.classpath;

import xianzhan.jvmjava.java.classfile.ClassFile;
import xianzhan.jvmjava.java.util.CollectionUtils;
import xianzhan.jvmjava.java.util.PathUtils;
import xianzhan.jvmjava.java.util.StringUtils;

import java.util.List;

/**
 * xxx/xxx.jar;xxx/zzz.jar
 *
 * @author xianzhan
 * @since 2020-04-09
 */
class CompositeEntry implements Entry {

    private final List<Entry> entries;

    public CompositeEntry(String path) {
        String[] pathArr = StringUtils.split(path, PathUtils.PATH_SEPARATOR);
        entries = CollectionUtils.newArrayList(pathArr.length);
        for (String p : pathArr) {
            entries.add(Entry.newEntry(p));
        }
    }

    @Override
    public ClassFile readClass(String classname) {
        for (Entry entry : entries) {
            ClassFile classFile = entry.readClass(classname);
            if (classFile != null) {
                return classFile;
            }
        }
        return null;
    }
}
