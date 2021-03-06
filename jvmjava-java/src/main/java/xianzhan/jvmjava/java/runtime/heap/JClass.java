package xianzhan.jvmjava.java.runtime.heap;

import xianzhan.jvmjava.java.classfile.ClassFile;
import xianzhan.jvmjava.java.classloader.ClassLoader;
import xianzhan.jvmjava.java.runtime.Slot;
import xianzhan.jvmjava.java.util.AccessFlags;
import xianzhan.jvmjava.java.util.ClassUtils;
import xianzhan.jvmjava.java.util.Symbol;

import java.util.Objects;

/**
 * 放进方法区的类
 * <p>
 * name, superClassName and interfaceNames are all binary names(jvms8-4.2.1)
 *
 * @author xianzhan
 * @see java.lang.Class
 * @since 2020-06-19
 */
public class JClass {

    public final int           accessFlags;
    /**
     * this class name
     */
    public final String        name;
    public       String        superClassName;
    public       String[]      interfaceNames;
    public       JConstantPool constantPool;
    public       JField[]      fields;
    public       JMethod[]     methods;
    private      String        sourceFile;
    public       ClassLoader   loader;
    public       JClass        superClass;
    public       JClass[]      interfaces;
    public       int           instanceSlotCount;
    public       int     staticSlotCount;
    public       Slot[]  staticVars;
    private      boolean initStarted;
    private      JObject       jClass;

    /**
     * 用于加载普通对象
     *
     * @param cf 类文件
     */
    public JClass(ClassFile cf) {
        this.accessFlags = cf.accessFlags;
        this.name = cf.classname();
        this.superClassName = cf.superClassname();
        this.interfaceNames = cf.interfaceNames();
        this.constantPool = new JConstantPool(this, cf.cpInfo);
        this.fields = JField.newFields(this, cf.fields.fields);
        this.methods = JMethod.newMethods(this, cf.methods.methods);
        this.sourceFile = getSourceFile(cf);
    }

    /**
     * 用于加载数组类
     *
     * @param name   数组名
     * @param loader 加载器
     */
    public JClass(String name, ClassLoader loader) {
        this.accessFlags = AccessFlags.ACC_PUBLIC;
        this.name = name;
        this.superClassName = Symbol.CLASS_OBJ;
        this.superClass = loader.loadClass(Symbol.CLASS_OBJ);
        this.interfaceNames = new String[]{
                Symbol.CLASS_CLONEABLE,
                Symbol.CLASS_SERIALIZABLE
        };
        this.interfaces = new JClass[]{
                loader.loadClass(Symbol.CLASS_CLONEABLE),
                loader.loadClass(Symbol.CLASS_SERIALIZABLE)
        };
        this.constantPool = null;
        this.fields = null;
        this.methods = null;
        this.loader = loader;
    }

    /**
     * 用于加载原始类型
     *
     * @param accessFlags
     * @param name
     * @param loader
     * @param initStarted
     */
    public JClass(int accessFlags, String name, ClassLoader loader, boolean initStarted) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.loader = loader;
        this.initStarted = initStarted;
    }

    private String getSourceFile(ClassFile cf) {
        var sourceFile = cf.sourceFileAttribute();
        if (sourceFile != null) {
            return sourceFile.name;
        }
        return "Unknown";
    }

    public boolean isPublic() {
        return (accessFlags & AccessFlags.ACC_PUBLIC) != 0;
    }

    public boolean isFinal() {
        return (accessFlags & AccessFlags.ACC_FINAL) != 0;
    }

    public boolean isSuper() {
        return (accessFlags & AccessFlags.ACC_SUPER) != 0;
    }

    public boolean isInterface() {
        return (accessFlags & AccessFlags.ACC_INTERFACE) != 0;
    }

    public boolean isAbstract() {
        return (accessFlags & AccessFlags.ACC_ABSTRACT) != 0;
    }

    public boolean isSynthetic() {
        return (accessFlags & AccessFlags.ACC_SYNTHETIC) != 0;
    }

    public boolean isAnnotation() {
        return (accessFlags & AccessFlags.ACC_ANNOTATION) != 0;
    }

    public boolean isEnum() {
        return (accessFlags & AccessFlags.ACC_ENUM) != 0;
    }

    public boolean isPrimitive() {
        var descriptor = Symbol.primitiveDescriptor(name);
        return !descriptor.isEmpty();
    }

    public boolean isAccessibleTo(JClass other) {
        return isPublic() || getPackageName().equals(other.getPackageName());
    }

    public boolean isSubClassOf(JClass other) {
        var superClass = this.superClass;
        while (superClass != null) {
            if (Objects.equals(superClass, other)) {
                return true;
            }
            superClass = superClass.superClass;
        }
        return false;
    }

    public boolean isSuperClassOf(JClass other) {
        return other.isSubClassOf(this);
    }

    public boolean isSuperInterfaceOf(JClass iFace) {
        return iFace.isSubInterfaceOf(this);
    }

    public boolean isImplements(JClass iFace) {
        var superClass = this;
        while (superClass != null) {
            for (var i : superClass.interfaces) {
                if (i == iFace || i.isSubInterfaceOf(iFace)) {
                    return true;
                }
            }
            superClass = superClass.superClass;
        }

        return false;
    }

    public boolean isSubInterfaceOf(JClass iFace) {
        if (interfaces != null) {
            for (var superInterface : interfaces) {
                if (superInterface == iFace || superInterface.isSubInterfaceOf(iFace)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * jvms8 6.5.instanceof
     * jvms8 6.5.checkcast
     * <p>
     * - 数组可以强制转换成 Object 类型（因为数组的超类是 Object）。
     * - 数组可以强制转换成 Cloneable 和 Serializable 类型（因为数组实现了这两个接口）。
     * - 如果下面两个条件之一成立，类型为 []SC 的数组可以强制转换成类型为 []TC 的数组：
     * ---- - TC 和 SC 是同一个基本类型。
     * ---- - TC 和 SC 都是引用类型，且SC可以强制转换成TC。
     *
     * @param other other
     * @return boolean
     */
    public boolean isAssignableFrom(JClass other) {
        JClass s = other, t = this;
        if (s == t) {
            return true;
        }

        if (!s.isArray()) {
            if (!s.isInterface()) {
                // s is class
                if (!t.isInterface()) {
                    // t is not interface
                    return s.isSuperClassOf(t);
                } else {
                    // t is interface
                    return s.isImplements(t);
                }
            } else {
                // s is interface
                if (!t.isInterface()) {
                    // t is not interface
                    return t.isJlObject();
                } else {
                    // t is interface
                    return t.isSuperInterfaceOf(s);
                }
            }
        } else {
            // s is array
            if (!t.isArray()) {
                if (!t.isInterface()) {
                    // t is class
                    return t.isJlObject();
                } else {
                    // t is interface
                    return t.isJlCloneable() || t.isJiSerializable();
                }
            } else {
                // t is array
                var sc = s.componentClass();
                var tc = t.componentClass();
                return sc == tc || tc.isAssignableFrom(sc);
            }
        }
    }

    private boolean isJlObject() {
        return Symbol.CLASS_OBJ.equals(name);
    }

    private boolean isJlCloneable() {
        return Symbol.CLASS_CLONEABLE.equals(name);
    }

    private boolean isJiSerializable() {
        return Symbol.CLASS_SERIALIZABLE.equals(name);
    }

    // getters

    public String sourceFile() {
        return sourceFile;
    }

    public String javaName() {
        return name.replace('/', '.');
    }

    public JObject jClass() {
        return jClass;
    }

    public void jClass(JObject jClass) {
        this.jClass = jClass;
    }

    public String getPackageName() {
        var i = name.lastIndexOf('/');
        if (i >= 0) {
            return name.substring(0, i);
        }
        return "";
    }

    private JMethod getMethod(String name, String descriptor, boolean isStatic) {
        for (var c = this; c != null; c = c.superClass) {
            for (var method : c.methods) {
                if (method.isStatic() == isStatic &&
                    method.name.equals(name) &&
                    method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    public JMethod getMainMethod() {
        return getMethod(Symbol.METHOD_MAIN, Symbol.DESCRIPTOR_STR_ARR_V, true);
    }

    public JMethod getClinitMethod() {
        return getMethod(Symbol.METHOD_CLINIT, Symbol.DESCRIPTOR_V_V, true);
    }

    public JConstantPool constantPool() {
        return constantPool;
    }

    public Slot[] staticVars() {
        return staticVars;
    }

    public void startInit() {
        initStarted = true;
    }

    public boolean initStarted() {
        return initStarted;
    }

    public JObject newObject() {
        return new JObject(this);
    }

    public JObject newArray(int length) {
        if (!isArray()) {
            throw new RuntimeException("Not array class: " + name);
        }

        return switch (name) {
            case Symbol.DESCRIPTOR_ARR_BOOLEAN,
                    Symbol.DESCRIPTOR_ARR_BYTE -> new JObject(this, new byte[length]);
            case Symbol.DESCRIPTOR_ARR_CHAR -> new JObject(this, new char[length]);
            case Symbol.DESCRIPTOR_ARR_SHORT -> new JObject(this, new short[length]);
            case Symbol.DESCRIPTOR_ARR_INT -> new JObject(this, new int[length]);
            case Symbol.DESCRIPTOR_ARR_LONG -> new JObject(this, new long[length]);
            case Symbol.DESCRIPTOR_ARR_FLOAT -> new JObject(this, new float[length]);
            case Symbol.DESCRIPTOR_ARR_DOUBLE -> new JObject(this, new double[length]);
            default -> new JObject(this, new JObject[length]);
        };
    }

    // reflection

    public JField getField(String name, String descriptor, boolean isStatic) {
        for (var c = this; c != null; c = c.superClass) {
            for (var field : c.fields) {
                if (field.isStatic() == isStatic &&
                    field.name.equals(name) &&
                    field.descriptor.equals(descriptor)) {
                    return field;
                }
            }
        }
        return null;
    }

    public JObject getRefVar(String fieldName, String fieldDescriptor) {
        var field = getField(fieldName, fieldDescriptor, true);
        return staticVars[field.slotIdx].ref;
    }

    public void setRefVar(String fieldName, String fieldDescriptor, JObject ref) {
        var field = getField(fieldName, fieldDescriptor, true);
        staticVars[field.slotIdx] = new Slot(ref);
    }

    public JMethod getInstanceMethod(String name, String descriptor) {
        return getMethod(name, descriptor, false);
    }

    // array

    public boolean isArray() {
        return name.startsWith(Symbol.DESCRIPTOR_ARR);
    }

    public JClass componentClass() {
        var componentClassName = ClassUtils.getComponentClassName(name);
        return loader.loadClass(componentClassName);
    }

    public JClass arrayClass() {
        var arrayClassName = ClassUtils.getArrayClassName(name);
        return loader.loadClass(arrayClassName);
    }

    @Override
    public String toString() {
        return "JClass#" + name;
    }
}
