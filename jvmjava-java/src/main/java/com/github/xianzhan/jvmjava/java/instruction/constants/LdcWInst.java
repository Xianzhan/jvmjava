package com.github.xianzhan.jvmjava.java.instruction.constants;

/**
 * @author xianzhan
 * @see LdcInst
 * @since 2020-06-26
 */
public class LdcWInst extends LdcInst {

    public LdcWInst(int index) {
        super(index);
    }

    @Override
    public int offset() {
        return 3;
    }

    @Override
    public String toString() {
        return "ldc2";
    }
}
