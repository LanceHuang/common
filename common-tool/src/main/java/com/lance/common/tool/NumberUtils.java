package com.lance.common.tool;

/**
 * @author Lance
 */
public class NumberUtils {

    /**
     * 判断是否2的幂
     */
    public static boolean isPowerOfTwo(int value) {
        return (value & -value) == value;
    }

    /**
     * long值转换成byte数组
     */
    public static byte[] longToByteArray(long value) {
        byte[] result = new byte[8];
        for (int i = 0; i < 8; i++) {
            result[i] = (byte) value;
            value >>= 8;
        }
        return result;
    }

    /**
     * byte数组转换成long值
     */
    public static long byteArrayToLong(byte[] value) {
        long result = 0;
        for (int i = 7; i >= 0; i--) {
            result <<= 8;
            result |= (value[i] & 0xFF);
        }
        return result;
    }

    public static int max(Integer num1, Integer num2) {
        if (num1 == null) {
            num1 = 0;
        }
        if (num2 == null) {
            num2 = 0;
        }
        return Integer.max(num1, num2);
    }

    public static long max(Long num1, Long num2) {
        if (num1 == null) {
            num1 = 0L;
        }
        if (num2 == null) {
            num2 = 0L;
        }
        return Long.max(num1, num2);
    }
}
