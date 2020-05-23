package com.github.xianzhan.jvmjava.java.classfile.cp;

import com.github.xianzhan.jvmjava.java.classfile.ConstantInfo;

/**
 * MUTF-8(Modified UTF-8) 编码
 * 与 UTF-8 大致相同, 但并不兼容
 * 1. null 字符(代码点 U+0000) 会被编码成 2 字节: 0xC0 0x80
 * 2. 补充字符(Supplementary Characters, 代码点大于 U+FFFF 的 Unicode 字符)
 *     按 UTF-16 拆分为代理对(Surrogate Pair)分别编码
 *
 * @author xianzhan
 * @since 2020-05-17
 */
public class Utf8 extends ConstantInfo {

    public final byte[] bytes;

    public Utf8(int tag, byte[] bytes) {
        super(tag);
        this.bytes = bytes;
    }

    public final String getString() {
        return new String(bytes);
    }

    @Override
    public String toString() {
        return getString();
    }
}
