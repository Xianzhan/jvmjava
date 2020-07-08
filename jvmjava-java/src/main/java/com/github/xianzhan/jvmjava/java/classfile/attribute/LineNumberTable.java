package com.github.xianzhan.jvmjava.java.classfile.attribute;

import com.github.xianzhan.jvmjava.java.classfile.Attribute;

/**
 * LineNumberTable_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 line_number_table_length;
 *     {
 *         u2 start_pc;
 *         u2 line_number;
 *     } line_number_table[line_number_table_length];
 * }
 * @author xianzhan
 * @since 2020-05-18
 */
public class LineNumberTable extends Attribute {

    public final Line[] lines;

    public LineNumberTable(Line[] lines) {
        this.lines = lines;
    }

    public int getLineNumber(int pc) {
        for (var line : lines) {
            var startPc = line.startPc;
            if (pc >= startPc) {
                return line.lineNumber;
            }
        }
        return -1;
    }

    public static class Line {

        public final int startPc;
        public final int lineNumber;

        public Line(int startPc, int lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }
    }
}
