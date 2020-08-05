package xianzhan.jvmjava.java.instruction.extended;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;
import xianzhan.jvmjava.java.instruction.control.GotoInst;

/**
 * goto_w 指令和 goto 指令的唯一区别就是索引从 2 字节变成了 4 字节。
 *
 * @author xianzhan
 * @see GotoInst
 * @since 2020-06-17
 */
public class GotoWInst implements Instruction {

    public final int offset;

    public GotoWInst(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute(Frame frame) {
        branch(frame, offset);
    }

    @Override
    public int offset() {
        return 5;
    }
}
