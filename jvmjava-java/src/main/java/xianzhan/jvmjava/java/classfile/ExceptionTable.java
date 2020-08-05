package xianzhan.jvmjava.java.classfile;

/**
 * @author xianzhan
 * @since 2020-05-18
 */
public class ExceptionTable {

    public final Exception[] exceptions;

    public ExceptionTable(Exception[] exceptions) {
        this.exceptions = exceptions;
    }
}
