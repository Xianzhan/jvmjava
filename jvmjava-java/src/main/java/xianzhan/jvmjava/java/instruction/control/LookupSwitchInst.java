package xianzhan.jvmjava.java.instruction.control;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

import java.util.Map;

/**
 * @author xianzhan
 * @since 2020-06-17
 */
public class LookupSwitchInst implements Instruction {

    public final int                   offset;
    public final int                   defaultOffset;
    public final int                   pairsCnt;
    public final Map<Integer, Integer> table;

    public LookupSwitchInst(int offset, int defaultOffset, int pairsCnt, Map<Integer, Integer> table) {
        this.offset = offset;
        this.defaultOffset = defaultOffset;
        this.pairsCnt = pairsCnt;
        this.table = table;
    }

    @Override
    public void execute(Frame frame) {
        var operandStack = frame.operandStack();
        var tmp = operandStack.popInt();
        var jump = table.getOrDefault(tmp, defaultOffset);
        branch(frame, jump);
    }

    @Override
    public int offset() {
        return offset;
    }
}
