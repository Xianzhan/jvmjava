package xianzhan.jvmjava.java.runtime.heap;

/**
 * 类符号引用
 * 运行时常量池 symbolic reference
 * <p>
 * cp字段存放符号引用所在的运行时常量池指针，这样就可以通
 * 过符号引用访问到运行时常量池，进一步又可以访问到类数据。
 *
 * @author xianzhan
 * @since 2020-06-24
 */
public class CpSymRef {

    protected JConstantPool cp;
    protected JClass        clazz;
    protected String        className;

    public JClass resolvedClass() {
        if (clazz == null) {
            resolveClassRef();
        }
        return clazz;
    }

    private void resolveClassRef() {
        var d = cp.clazz;
        var c = d.loader.loadClass(className);
        if (!c.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }

        clazz = c;
    }
}
