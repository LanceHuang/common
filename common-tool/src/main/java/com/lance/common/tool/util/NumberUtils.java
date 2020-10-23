package com.lance.common.tool.util;

/**
 * @author Lance
 */
public class NumberUtils {

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
}
