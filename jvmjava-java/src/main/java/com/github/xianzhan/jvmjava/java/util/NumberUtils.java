package com.github.xianzhan.jvmjava.java.util;

/**
 * 数字工具类
 *
 * @author xianzhan
 * @since 2020-05-24
 */
public class NumberUtils {

    public static final long MASK_INT = 0xFFFFFFFFL;

    public static final int BIT_32 = 32;

    /**
     * 31
     * int 变量只有 32 位, 所以只取前 5 个比特就足够表示位移位数了
     */
    public static final int MASK_SH = 0x1F;

    /**
     * 63
     * long 变量只有 64 位, 左移只取前 6 个比特就足够表示唯一位数了
     */
    public static final int MASK_LSH = 0x3F;

    /**
     * 返回高位数字
     *
     * @param l long
     * @return 高位数字
     */
    public static int highInt(long l) {
        return (int) (l >> BIT_32);
    }

    /**
     * 返回低位数字
     *
     * @param l long
     * @return 低位数字
     */
    public static int lowInt(long l) {
        return (int) (l & MASK_INT);
    }

    /**
     * 将高位数字和低位数字合并成 long
     *
     * @param high 高位
     * @param low  低位
     * @return long
     */
    public static long concatLong(int high, int low) {
        long h = (high & MASK_INT) << BIT_32;
        long l = low & MASK_INT;
        return h | l;
    }

    public static String sign(int n) {
        return n < 0 ? "-" : "+";
    }
}
