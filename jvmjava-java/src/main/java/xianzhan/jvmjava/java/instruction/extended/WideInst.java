package xianzhan.jvmjava.java.instruction.extended;

import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.runtime.Frame;

/**
 * 加载类指令、存储类指令、ret指令和iinc指令需要按索引访问
 * 局部变量表，索引以uint8的形式存在字节码中。对于大部分方法来
 * 说，局部变量表大小都不会超过256，所以用一字节来表示索引就
 * 够了。但是如果有方法的局部变量表超过这限制呢？Java虚拟机规
 * 范定义了wide指令来扩展前述指令。
 *
 * @author xianzhan
 * @since 2020-06-17
 */
public class WideInst implements Instruction {

    public final int         offset;
    public final Instruction inst;

    public WideInst(int offset, Instruction inst) {
        this.offset = offset;
        this.inst = inst;
    }

    @Override
    public void execute(Frame frame) {
        inst.execute(frame);
    }

    @Override
    public int offset() {
        return offset;
    }
}
