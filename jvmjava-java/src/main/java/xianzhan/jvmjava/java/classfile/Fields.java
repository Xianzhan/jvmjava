package xianzhan.jvmjava.java.classfile;

import java.util.Arrays;

/**
 * @author xianzhan
 * @since 2020-05-18
 */
public class Fields {

    public final Field[] fields;

    public Fields(int fieldCount) {
        this.fields = new Field[fieldCount];
    }

    @Override
    public String toString() {
        return Arrays.toString(fields);
    }
}
