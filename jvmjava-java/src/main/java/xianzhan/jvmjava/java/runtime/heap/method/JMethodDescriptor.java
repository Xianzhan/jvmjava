package xianzhan.jvmjava.java.runtime.heap.method;

import xianzhan.jvmjava.java.util.CollectionUtils;

import java.util.List;

/**
 * @author xianzhan
 * @since 2020-06-28
 */
public class JMethodDescriptor {

    public final List<String> parameterType;
    public       String       returnType;

    public JMethodDescriptor() {
        parameterType = CollectionUtils.newArrayList();
    }

    public void addParameterType(String t) {
        parameterType.add(t);
    }
}
