package xianzhan.jvmjava.java.classfile.attribute;

import xianzhan.jvmjava.java.classfile.Attribute;

/**
 * 标记, 不包含任何数据
 * ClassFile, field_info, method_info
 * <p>
 * 标记源文件不存在、由编译器生成的类成员,
 * 引入 Synthetic 属性主要是为了支持嵌套类和嵌套接口
 *
 * @author xianzhan
 * @since 2020-05-23
 */
public class Synthetic extends Attribute {

}
