package com.muyuan.common.core.util;

/**
 * @ClassName MathUtil
 * Description 数学工具
 * @Author 2456910384
 * @Date 2022/5/17 9:25
 * @Version 1.0
 */
public class MathUtil {

    public static long dtNextPow2(long v) {
        v--;
        v |= v >> 1;
        v |= v >> 2;
        v |= v >> 4;
        v |= v >> 8;
        v |= v >> 16;
        v++;
        return v;
    }

    public static int bit(long x) {
        int i = 0;
        while (x > 0) {
            x >>= 1;
            i++;
        }
        return i;
    }

}
