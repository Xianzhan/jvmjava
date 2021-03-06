package xianzhan.jvmjava.java.classfile.attribute;

import xianzhan.jvmjava.java.classfile.Attribute;
import xianzhan.jvmjava.java.classfile.Attributes;
import xianzhan.jvmjava.java.classfile.ExceptionTable;
import xianzhan.jvmjava.java.instruction.Instruction;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Code_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 max_stack;
 *     u2 max_locals;
 *     u4 code_length;
 *     u1 code[code_length];
 *     u2 exception_table_length;
 *     {
 *         u2 start_pc;
 *         u2 end_pc;
 *         u2 handler_pc;
 *         u2 catch_type;
 *     } exception_table[exception_table_length];
 *     u2 attributes_count;
 *     attribute_info attributes[attributes_count];
 * }
 * @author xianzhan
 * @since 2020-05-18
 */
public class Code extends Attribute {

    /**
     * 操作栈的最大深度
     */
    public final int            maxStack;
    /***
     * 局部变量表大小
     */
    public final int            maxLocals;
    /**
     * 字节码
     */
    public final Instruction[]  instructions;
    /**
     * 异常表
     */
    public final ExceptionTable exceptionTable;
    public final Attributes     attributes;

    public Code(int maxStack,
                int maxLocals,
                Instruction[] instructions,
                ExceptionTable exceptionTable,
                Attributes attributes) {
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.instructions = instructions;
        this.exceptionTable = exceptionTable;
        this.attributes = attributes;
    }

    public Map<Integer, Instruction> codeMap() {
        Map<Integer, Instruction> map = new LinkedHashMap<>(instructions.length);
        int pc = 0;
        for (var inst : instructions) {
            map.put(pc, inst);
            pc += inst.offset();
        }
        return map;
    }

    public LineNumberTable lineNumberTableAttribute() {
        for (var attr : attributes.attributes) {
            if (attr instanceof LineNumberTable lineNumberTable) {
                return lineNumberTable;
            }
        }
        return null;
    }
}
