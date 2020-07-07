package com.github.xianzhan.jvmjava.java.classloader;

import com.github.xianzhan.jvmjava.java.classfile.ClassFile;
import com.github.xianzhan.jvmjava.java.classpath.Classpath;
import com.github.xianzhan.jvmjava.java.runtime.LocalVars;
import com.github.xianzhan.jvmjava.java.runtime.Slot;
import com.github.xianzhan.jvmjava.java.runtime.heap.JClass;
import com.github.xianzhan.jvmjava.java.runtime.heap.JField;
import com.github.xianzhan.jvmjava.java.runtime.heap.StringPool;
import com.github.xianzhan.jvmjava.java.util.AccessFlags;
import com.github.xianzhan.jvmjava.java.util.CollectionUtils;
import com.github.xianzhan.jvmjava.java.util.Symbol;

import java.util.Map;

/**
 * class names:
 * ----- primitive types: boolean, byte, int ...
 * ----- primitive arrays: [Z, [B, [I ...
 * ----- non-array classes: java/lang/Object ...
 * ----- array classes: [Ljava/lang/Object; ...
 *
 * @author xianzhan
 * @since 2020-06-23
 */
public class ClassLoader {

    private final Classpath           cp;
    private final Map<String, JClass> classMap;
    private final boolean             verboseClassFlag;

    public ClassLoader(Classpath cp, boolean verboseClassFlag) {
        this.cp = cp;
        this.classMap = CollectionUtils.newHashMap();
        this.verboseClassFlag = verboseClassFlag;

        loadBasicClasses();
        loadPrimitiveClasses();
    }

    private void loadBasicClasses() {
        var jlClass = loadClass(Symbol.CLASS_CLASS);
        for (var clazz : classMap.values()) {
            if (clazz.jClass() == null) {
                clazz.jClass(jlClass.newObject());
                clazz.jClass().extra(clazz);
            }
        }
    }

    private void loadPrimitiveClasses() {
        loadPrimitiveClass(Symbol.VOID);
        loadPrimitiveClass(Symbol.BOOLEAN);
        loadPrimitiveClass(Symbol.BYTE);
        loadPrimitiveClass(Symbol.SHORT);
        loadPrimitiveClass(Symbol.INT);
        loadPrimitiveClass(Symbol.LONG);
        loadPrimitiveClass(Symbol.CHAR);
        loadPrimitiveClass(Symbol.FLOAT);
        loadPrimitiveClass(Symbol.DOUBLE);
    }

    private void loadPrimitiveClass(String className) {
        var clazz = new JClass(AccessFlags.ACC_PUBLIC, className, this, true);
        var jClass = classMap.get(Symbol.CLASS_CLASS).newObject();
        clazz.jClass(jClass);
        clazz.jClass().extra(clazz);
        classMap.put(className, clazz);
    }

    public JClass loadClass(String name) {
        var clazz = classMap.get(name);
        if (clazz != null) {
            return clazz;
        }

        if (name.startsWith(Symbol.DESCRIPTOR_ARR)) {
            // array class
            clazz = loadArrayClass(name);
        } else {
            clazz = loadNonArrayClass(name);
        }

        // Object::getClass
        var jlClass = classMap.get(Symbol.CLASS_CLASS);
        if (jlClass != null) {
            clazz.jClass(jlClass.newObject());
            clazz.jClass().extra(clazz);
        }

        return clazz;
    }

    private JClass loadArrayClass(String name) {
        var clazz = new JClass(name, this);
        classMap.put(name, clazz);
        return clazz;
    }

    private JClass loadNonArrayClass(String name) {
        var classFile = readClass(name);
        var clazz = defineClass(classFile);
        link(clazz);

        if (verboseClassFlag) {
            System.out.println("[Loaded %s from %s]".formatted(name, classFile));
        }

        return clazz;
    }

    private ClassFile readClass(String name) {
        var classFile = cp.readClass(name);
        if (classFile == null) {
            throw new RuntimeException("java.lang.ClassNotFoundException: " + name);
        }
        return classFile;
    }

    private JClass defineClass(ClassFile classFile) {
        var clazz = new JClass(classFile);
        clazz.loader = this;
        resolveSuperClass(clazz);
        resoleInterfaces(clazz);

        classMap.put(clazz.name, clazz);
        return clazz;
    }

    private void resoleInterfaces(JClass clazz) {
        var interfaceNames = clazz.interfaceNames;
        var length = interfaceNames.length;
        if (length > 0) {
            clazz.interfaces = new JClass[length];
            for (int i = 0; i < length; i++) {
                var interfaceName = interfaceNames[i];
                clazz.interfaces[i] = clazz.loader.loadClass(interfaceName);
            }
        }
    }

    private void resolveSuperClass(JClass clazz) {
        if (!Symbol.CLASS_OBJ.equals(clazz.name)) {
            clazz.superClass = clazz.loader.loadClass(clazz.superClassName);
        }
    }

    private void link(JClass clazz) {
        verify(clazz);
        prepare(clazz);
    }

    private void verify(JClass clazz) {
        // todo 验证
    }

    private void prepare(JClass clazz) {
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitStaticVars(clazz);
    }

    private void calcInstanceFieldSlotIds(JClass clazz) {
        int slotCount = 0;
        var superClass = clazz.superClass;
        if (superClass != null) {
            slotCount = superClass.instanceSlotCount;
        }

        for (var field : clazz.fields) {
            if (!field.isStatic()) {
                field.slotIdx = slotCount++;
                if (field.isLongOrDouble()) {
                    slotCount++;
                }
            }
        }
        clazz.instanceSlotCount = slotCount;
    }

    private void calcStaticFieldSlotIds(JClass clazz) {
        int slotCount = 0;
        for (var field : clazz.fields) {
            if (field.isStatic()) {
                field.slotIdx = slotCount++;
                if (field.isLongOrDouble()) {
                    slotCount++;
                }
            }
        }
        clazz.staticSlotCount = slotCount;
    }

    private void allocAndInitStaticVars(JClass clazz) {
        clazz.staticVars = new Slot[clazz.staticSlotCount];
        for (var field : clazz.fields) {
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(clazz, field);
            }
        }
    }

    private void initStaticFinalVar(JClass clazz, JField field) {
        var staticVars = new LocalVars(clazz.staticVars);
        var cp = clazz.constantPool();
        var cpIndex = field.constValueIndex();
        var slotIdx = field.slotIdx;

        if (cpIndex > 0) {
            switch (field.descriptor()) {
                case Symbol.DESCRIPTOR_BOOLEAN,
                        Symbol.DESCRIPTOR_BYTE,
                        Symbol.DESCRIPTOR_CHAR,
                        Symbol.DESCRIPTOR_SHORT,
                        Symbol.DESCRIPTOR_INT -> {
                    int i = (int) cp.getConstant(cpIndex).val;
                    staticVars.setInt(slotIdx, i);
                }
                case Symbol.DESCRIPTOR_LONG -> {
                    long l = (long) cp.getConstant(cpIndex).val;
                    staticVars.setLong(slotIdx, l);
                }
                case Symbol.DESCRIPTOR_FLOAT -> {
                    float f = (float) cp.getConstant(cpIndex).val;
                    staticVars.setFloat(slotIdx, f);
                }
                case Symbol.DESCRIPTOR_DOUBLE -> {
                    double d = (double) cp.getConstant(cpIndex).val;
                    staticVars.setDouble(slotIdx, d);
                }
                case Symbol.DESCRIPTOR_STR -> {
                    var str = (String) cp.getConstant(cpIndex).val;
                    var jStr = StringPool.jString(clazz.loader, str);
                    staticVars.setRef(slotIdx, jStr);
                }
                default -> throw new RuntimeException("todo");
            }
        }
    }
}
