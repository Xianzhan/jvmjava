package xianzhan.jvmjava.java.classpath;

import xianzhan.jvmjava.java.classfile.ClassFile;

/**
 * xxx/dir/*
 *
 * @author xianzhan
 * @since 2020-04-09
 */
class WildcardEntry implements Entry {

    private final Entry entry;

    public WildcardEntry(String path) {
        int length = path.length();
        String dirPath = path.substring(0, length - 2);
        entry = Entry.newEntry(dirPath);
    }

    @Override
    public ClassFile readClass(String classname) {
        return entry.readClass(classname);
    }
}
