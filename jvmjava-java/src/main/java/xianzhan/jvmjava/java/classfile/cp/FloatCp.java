package xianzhan.jvmjava.java.classfile.cp;

import xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * IEEE754 单精度浮点数常量
 *
 * @author xianzhan
 * @since 2020-05-17
 */
public class FloatCp extends ConstantInfo {

    public final float val;

    public FloatCp(int tag, float val) {
        super(tag);
        this.val = val;
    }
}