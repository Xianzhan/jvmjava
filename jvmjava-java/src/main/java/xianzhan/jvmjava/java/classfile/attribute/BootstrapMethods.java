package xianzhan.jvmjava.java.classfile.attribute;

import xianzhan.jvmjava.java.classfile.Attribute;

/**
 * @author xianzhan
 * @since 2020-05-18
 */
public class BootstrapMethods extends Attribute {

    public final BootstrapMethod[] methods;

    public BootstrapMethods(BootstrapMethod[] methods) {
        this.methods = methods;
    }

    public static class BootstrapMethod {
        public final Integer   bootstrapMethodRefInx;
        public final Integer[] argsRefs;

        public BootstrapMethod(Integer bootstrapMethodRefInx, Integer[] argsRefs) {
            this.bootstrapMethodRefInx = bootstrapMethodRefInx;
            this.argsRefs = argsRefs;
        }
    }
}
