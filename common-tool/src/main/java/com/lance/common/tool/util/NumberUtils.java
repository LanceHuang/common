package com.lance.common.tool.util;

/**
 * @author Lance
 */
public class NumberUtils {

    // todo 有没有一种更优雅的做法？

    public static String byteToBinary(byte value) {
        StringBuilder sb = new StringBuilder();
        int bitNum = 8; // 位数
        while (bitNum-- > 0) {
            sb.append(value >>> 7);
            value <<= 1;
        }
        return sb.toString();
    }

    public static String shortToBinary(short value) {
        StringBuilder sb = new StringBuilder();
        int bitNum = 16; // 位数
        while (bitNum-- > 0) {
            sb.append((value & 0x8000) >>> 15);
            value <<= 1;
        }
        return sb.toString();
    }

    public static String intToBinary(int value) {
        StringBuilder sb = new StringBuilder();
        int bitNum = 32; // 位数
        while (bitNum-- > 0) {
            sb.append((value & 0x80000000) >>> 31);
            value <<= 1;
        }
        return sb.toString();
    }

    public static String longToBinary(long value) {
        StringBuilder sb = new StringBuilder();
        int bitNum = 64; // 位数
        while (bitNum-- > 0) {
            sb.append((value & 0x8000000000000000L) >>> 63);
            value <<= 1;
        }
        return sb.toString();
    }

}
