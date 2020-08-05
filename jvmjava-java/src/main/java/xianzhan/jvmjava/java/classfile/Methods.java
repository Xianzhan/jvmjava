package xianzhan.jvmjava.java.classfile;

import java.util.Arrays;

/**
 * @author xianzhan
 * @since 2020-05-18
 */
public class Methods {

    public final Method[] methods;

    public Methods(int methodCount) {
        this.methods = new Method[methodCount];
    }

    @Override
    public String toString() {
        return Arrays.toString(methods);
    }
}
