package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.util.AccessFlags;
import com.github.xianzhan.jvmjava.java.classfile.Member;
import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.runtime.heap.method.JMethodDescriptorParser;
import com.github.xianzhan.jvmjava.java.util.Symbol;

import java.util.Map;

/**
 * @author xianzhan
 * @since 2020-06-23
 */
public class JMethod extends JClassMember {

    public  int                       maxStack;
    public  int                       maxLocals;
    private Map<Integer, Instruction> codeMap;
    /**
     * 参数插槽个数
     */
    private int                       argSlotCount;

    public static JMethod[] newMethods(JClass clazz, Member[] cfMethods) {
        final int len = cfMethods.length;
        var methods = new JMethod[len];
        for (int i = 0; i < len; i++) {
            var cfMethod = cfMethods[i];
            var method = new JMethod();
            method.clazz = clazz;
            method.copyMemberInfo(cfMethod);
            method.copyAttributes(cfMethod);
            method.calcArgSlotCount();

            methods[i] = method;
        }
        return methods;
    }

    public boolean isSynchronized() {
        return (accessFlags & AccessFlags.ACC_SYNCHRONIZED) != 0;
    }

    public boolean isBridge() {
        return (accessFlags & AccessFlags.ACC_BRIDGE) != 0;
    }

    public boolean isVarargs() {
        return (accessFlags & AccessFlags.ACC_VARARGS) != 0;
    }

    public boolean isNative() {
        return (accessFlags & AccessFlags.ACC_NATIVE) != 0;
    }

    public boolean isAbstract() {
        return (accessFlags & AccessFlags.ACC_ABSTRACT) != 0;
    }

    public boolean isStrict() {
        return (accessFlags & AccessFlags.ACC_STRICT) != 0;
    }

    public int maxStack() {
        return maxStack;
    }

    public int maxLocals() {
        return maxLocals;
    }

    public Map<Integer, Instruction> codeMap() {
        return codeMap;
    }

    public int argSlotCount() {
        return argSlotCount;
    }

    private void copyAttributes(Member method) {
        var code = method.codeAttribute();
        if (code != null) {
            maxStack = code.maxStack;
            maxLocals = code.maxLocals;
            codeMap = code.codeMap();
        }
    }

    private void calcArgSlotCount() {
        var parsedDescriptor = JMethodDescriptorParser.parseMethodDescriptor(descriptor);
        for (var paramType : parsedDescriptor.parameterType) {
            argSlotCount++;
            if (Symbol.DESCRIPTOR_LONG.equals(paramType) || Symbol.DESCRIPTOR_DOUBLE.equals(paramType)) {
                argSlotCount++;
            }
        }

        if (!isStatic()) {
            // `this` reference
            argSlotCount++;
        }
    }

    @Override
    public String toString() {
        return "JMethod#" + clazz.name + "." + name + descriptor;
    }
}
