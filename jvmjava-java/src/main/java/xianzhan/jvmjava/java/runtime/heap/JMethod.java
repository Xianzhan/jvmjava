package xianzhan.jvmjava.java.runtime.heap;

import xianzhan.jvmjava.java.bytecode.ByteCodes;
import xianzhan.jvmjava.java.classfile.InstructionReader;
import xianzhan.jvmjava.java.classfile.Member;
import xianzhan.jvmjava.java.classfile.attribute.LineNumberTable;
import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.heap.method.JMethodDescriptorParser;
import xianzhan.jvmjava.java.util.AccessFlags;
import xianzhan.jvmjava.java.util.Symbol;

import java.util.LinkedHashMap;
import java.util.List;
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

    private LineNumberTable    lineNumberTable;
    private ExceptionHandler[] exceptionTable;

    public static JMethod[] newMethods(JClass clazz, Member[] cfMethods) {
        final int len = cfMethods.length;
        var methods = new JMethod[len];
        for (int i = 0; i < len; i++) {
            var cfMethod = cfMethods[i];
            methods[i] = newMethod(clazz, cfMethod);
        }
        return methods;
    }

    private static JMethod newMethod(JClass clazz, Member cfMethod) {
        var method = new JMethod();
        method.clazz = clazz;
        method.copyMemberInfo(cfMethod);
        method.copyAttributes(cfMethod);

        var md = JMethodDescriptorParser.parseMethodDescriptor(method.descriptor);
        method.calcArgSlotCount(md.parameterType);
        if (method.isNative()) {
            method.injectCodeAttribute(md.returnType);
        }
        return method;
    }

    private void injectCodeAttribute(String returnType) {
        // todo 本地方法帧的操作数栈至少要能容纳返回值，为了简化代码，暂时给 maxStack 字段赋值为 4。
        maxStack = 4;
        maxLocals = argSlotCount;
        var type = returnType.substring(0, 1);
        switch (type) {
            case Symbol.DESCRIPTOR_VOID -> codeMap = genCode(new int[]{ByteCodes.invokenative, ByteCodes.return_});
            case Symbol.DESCRIPTOR_REF,
                    Symbol.DESCRIPTOR_ARR -> codeMap = genCode(new int[]{ByteCodes.invokenative, ByteCodes.areturn});
            case Symbol.DESCRIPTOR_DOUBLE -> codeMap = genCode(new int[]{ByteCodes.invokenative, ByteCodes.dreturn});
            case Symbol.DESCRIPTOR_FLOAT -> codeMap = genCode(new int[]{ByteCodes.invokenative, ByteCodes.freturn});
            case Symbol.DESCRIPTOR_LONG -> codeMap = genCode(new int[]{ByteCodes.invokenative, ByteCodes.lreturn});
            default -> codeMap = genCode(new int[]{ByteCodes.invokenative, ByteCodes.ireturn});
        }
    }

    private Map<Integer, Instruction> genCode(int[] opCodes) {
        Map<Integer, Instruction> codeMap = new LinkedHashMap<>(opCodes.length);
        int pc = 0;
        for (var opCode : opCodes) {
            var instruction = InstructionReader.read(opCode);
            codeMap.put(pc, instruction);
            pc += instruction.offset();
        }
        return codeMap;
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

    public int findExceptionHandler(JClass exClass, int pc) {
        var handler = ExceptionHandler.findExceptionHandler(exceptionTable, exClass, pc);
        if (handler != null) {
            return handler.handlerPc();
        }
        return -1;
    }

    public int getLineNumber(int pc) {
        if (isNative()) {
            return -2;
        }
        if (lineNumberTable == null) {
            return -1;
        }
        return lineNumberTable.getLineNumber(pc);
    }

    private void copyAttributes(Member method) {
        var code = method.codeAttribute();
        if (code != null) {
            maxStack = code.maxStack;
            maxLocals = code.maxLocals;
            codeMap = code.codeMap();
            lineNumberTable = code.lineNumberTableAttribute();
            exceptionTable = ExceptionHandler.newExceptionTable(code.exceptionTable, clazz.constantPool);
        }
    }

    private void calcArgSlotCount(List<String> parameterType) {
        for (var paramType : parameterType) {
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
