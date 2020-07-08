package com.github.xianzhan.jvmjava.java.classfile;

import com.github.xianzhan.jvmjava.java.classfile.attribute.BootstrapMethods;
import com.github.xianzhan.jvmjava.java.classfile.attribute.SourceFile;

import java.util.stream.Stream;

/**
 * ClassFile {
 *     u4             magic;
 *     u2             minor_version;
 *     u2             major_version;
 *     u2             constant_pool_count;
 *     cp_info        constant_pool[constant_pool_count-1];
 *     u2             access_flags;
 *     u2             this_class;
 *     u2             super_class;
 *     u2             interfaces_count;
 *     u2             interfaces[interfaces_count];
 *     u2             fields_count;
 *     field_info     fields[fields_count];
 *     u2             methods_count;
 *     method_info    methods[methods_count];
 *     u2             attributes_count;
 *     attribute_info attributes[attributes_count];
 * }
 *
 * @author xianzhan
 * @since 2020-04-09
 */
public class ClassFile {

    /**
     * class 文件魔数
     */
    public static final int MAGIC_NUM = 0xCAFEBABE;

    /**
     * 魔数, class 文件开头四个字节的值为 0xCAFEBABE
     */
    public final int          magic;
    /**
     * 次版本号
     */
    public final int          minorVersion;
    /**
     * 主版本号
     */
    public final int          majorVersion;
    /**
     * 常量池大小
     */
    public final int          constantPoolSize;
    /**
     * 常量池
     */
    public final ConstantPool cpInfo;
    /**
     * 访问模式
     */
    public final int          accessFlags;
    /**
     * 类名
     * class 文件存储的类名类似完全限定名, 但是把点换成了斜线
     * Java 语言规范把这种名字叫做二进制名(binary names)
     */
    public final int          thisClass;
    /**
     * 超类
     * 除了 java.lang.Object 之外, 其他类都有超类,
     * 所以 supperClass 只在 Object.class 中是 0
     */
    public final int          superClass;
    /**
     * 实现接口个数
     */
    public final int          interfacesCount;
    /**
     * 接口列表
     */
    public final Interfaces   interfaces;
    /**
     * 字段个数
     */
    public final int          fieldCount;
    /**
     * 字段
     */
    public final Fields       fields;
    /**
     * 方法个数
     */
    public final int          methodsCount;
    /**
     * 方法列表
     */
    public final Methods      methods;
    /**
     * 属性个数
     */
    public final int          attributesCount;
    /**
     * 属性
     */
    public final Attributes   attributes;

    public ClassFile(int magic,
                     int minorVersion,
                     int majorVersion,
                     int constantPoolSize,
                     ConstantPool cpInfo,
                     int accessFlags,
                     int thisClass,
                     int superClass,
                     int interfacesCount,
                     Interfaces interfaces,
                     int fieldCount,
                     Fields fields,
                     int methodsCount,
                     Methods methods,
                     int attributesCount,
                     Attributes attributes) {
        this.magic = magic;
        this.minorVersion = minorVersion;
        this.majorVersion = majorVersion;
        this.constantPoolSize = constantPoolSize;
        this.cpInfo = cpInfo;
        this.accessFlags = accessFlags;
        this.thisClass = thisClass;
        this.superClass = superClass;
        this.interfacesCount = interfacesCount;
        this.interfaces = interfaces;
        this.fieldCount = fieldCount;
        this.fields = fields;
        this.methodsCount = methodsCount;
        this.methods = methods;
        this.attributesCount = attributesCount;
        this.attributes = attributes;
    }

    /**
     * @return 例: java/lang/String
     */
    public String classname() {
        return cpInfo.getClassName(thisClass);
    }

    /**
     * @return 例: java/lang/Object
     */
    public String superClassname() {
        if (superClass > 0) {
            return cpInfo.getClassName(superClass);
        }
        return "";
    }

    public SourceFile sourceFileAttribute() {
        for (var attr : attributes.attributes) {
            if (attr instanceof SourceFile sourceFile) {
                return sourceFile;
            }
        }
        return null;
    }

    /**
     * @return 接口列表名称数组
     */
    public String[] interfaceNames() {
        return Stream.of(interfaces.interfaces)
                .map(Interface::getName)
                .toArray(String[]::new);
    }

    public BootstrapMethods getBootstrapMethods() {
        for (var attr : attributes.attributes) {
            if (attr instanceof BootstrapMethods) {
                return (BootstrapMethods) attr;
            }
        }
        return null;
    }

    public void printInfo() {
        String classInfo = """
                version: %d.%d
                constants count: %d
                access flags: %x
                this class: %s
                super class: %s
                interfaces: %s
                fields count: %d
                fields: %s
                methods count: %d
                methods: %s
                """.formatted(
                majorVersion,
                minorVersion,
                constantPoolSize,
                accessFlags,
                thisClass,
                superClass,
                interfaces,
                fieldCount,
                fields,
                methodsCount,
                methods
        );
        System.out.println(classInfo);
    }

    @Override
    public String toString() {
        return "ClassFile#" + classname();
    }
}
