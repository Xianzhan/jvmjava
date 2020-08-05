package xianzhan.jvmjava.java.classfile;

/**
 * @author xianzhan
 * @since 2020-05-18
 */
public class Attributes {

    public final Attribute[] attributes;

    public Attributes(int size) {
        this.attributes = new Attribute[size];
    }
}
