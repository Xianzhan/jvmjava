package xianzhan.jvmjava.java.classfile.cp;

import xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class LongCp extends ConstantInfo {

    public final long val;

    public LongCp(int tag, long val) {
        super(tag);
        this.val = val;
    }
}