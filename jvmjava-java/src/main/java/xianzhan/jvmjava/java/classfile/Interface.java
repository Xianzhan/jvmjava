package xianzhan.jvmjava.java.classfile;

/**
 * @author xianzhan
 * @since 2020-05-17
 */
public class Interface {
    public final String name;

    public Interface(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
