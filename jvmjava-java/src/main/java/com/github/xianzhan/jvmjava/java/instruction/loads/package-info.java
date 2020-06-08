/**
 * 加载指令
 * <p>
 * 加载指令从局部变量表获取变量，然后推入操作数栈顶。加载
 * 指令共33条，按照所操作变量的类型可以分为6类：aload系列指令
 * 操作引用类型变量、dload系列操作double类型变量、fload系列操作
 * float变量、iload系列操作int变量、lload系列操作long变量、xaload操
 * 作数组。
 *
 * @see com.github.xianzhan.jvmjava.java.instruction.stores
 */
package com.github.xianzhan.jvmjava.java.instruction.loads;