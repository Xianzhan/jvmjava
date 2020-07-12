package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.classfile.ExceptionTable;

/**
 * 异常处理类
 *
 * @author xianzhan
 * @since 2020-07-08
 */
public class ExceptionHandler {

    private int        startPc;
    private int        endPc;
    private int        handlerPc;
    private CpClassRef catchType;

    public ExceptionHandler(int startPc, int endPc, int handlerPc, CpClassRef catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }

    public static ExceptionHandler[] newExceptionTable(ExceptionTable entries, JConstantPool cp) {
        var length = entries.exceptions.length;
        var table = new ExceptionHandler[length];
        for (int i = 0; i < length; i++) {
            var exception = entries.exceptions[i];
            table[i] = new ExceptionHandler(
                    exception.startPc,
                    exception.endPc,
                    exception.handlerPc,
                    getCatchType(exception.catchType, cp)
            );
        }

        return table;
    }

    public static ExceptionHandler findExceptionHandler(ExceptionHandler[] exceptionTable, JClass exClass, int pc) {
        for (var handler : exceptionTable) {
            // jvms: The start_pc is inclusive and end_pc is exclusive
            if (pc >= handler.startPc && pc < handler.endPc) {
                var catchType = handler.catchType;
                if (catchType == null) {
                    return handler;
                }
                var catchClass = catchType.resolvedClass();
                if (catchClass == exClass || catchClass.isSuperClassOf(exClass)) {
                    return handler;
                }
            }
        }
        return null;
    }

    public int handlerPc() {
        return handlerPc;
    }

    private static CpClassRef getCatchType(int index, JConstantPool cp) {
        if (index == 0) {
            // catch all
            return null;
        }
        var constant = cp.getConstant(index);
        return (CpClassRef) constant;
    }
}
