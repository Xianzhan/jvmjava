package xianzhan.jvmjava.java.classpath;

import xianzhan.jvmjava.java.classfile.ClassFile;
import xianzhan.jvmjava.java.util.PathUtils;
import xianzhan.jvmjava.java.util.StringUtils;

/**
 * Find class files
 *
 * @author xianzhan
 * @since 2020-04-09
 */
public interface Entry {

    /**
     * Find and load classes
     *
     * @param classname relative path of class file
     * @return ClassFile
     */
    ClassFile readClass(String classname);

    /**
     * Instantiate Entry subclasses.
     *
     * @param path file or path
     * @return entry instance
     */
    static Entry newEntry(String path) {
        if (StringUtils.contains(path, PathUtils.PATH_SEPARATOR)) {
            return new CompositeEntry(path);
        }
        if (StringUtils.endsWith(path, PathUtils.ALL)) {
            return new WildcardEntry(path);
        }
        if (PathUtils.isCompressedName(path)) {
            return new ZipEntry(path);
        }
        return new DirEntry(path);
    }
}
