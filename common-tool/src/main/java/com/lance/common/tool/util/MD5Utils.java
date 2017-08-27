package com.lance.common.tool.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Tool for transform string or byte array to md5.
 * Created by Lance on 2016/8/12.
 */
public class MD5Utils {

    private final static char[] lowDigits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f'
    };

    private final static char[] upperDigits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F'
    };

    private MD5Utils() {
    }

    /**
     * Transform string to md5 format(32bit lower case).
     *
     * @param str String expected to be transformed
     * @return String of md5 format
     */
    public static String md5(String str) {
        return md5(str.getBytes(), true, false);
    }

    /**
     * Transform string to md5 format.
     *
     * @param str String expected to be transformed
     * @return String of md5 format
     */
    public static String md5(String str, boolean upperCase) {
        return md5(str.getBytes(), true, upperCase);
    }

    /**
     * Transform string to md5 format(32bit lower case).
     *
     * @param str    String expected to be transformed
     * @param offset Offset of string
     * @param len    Length of string
     * @return String of md5 format
     */
    public static String md5(String str, int offset, int len) {
        return md5(str.substring(offset, offset + len).getBytes(), true, false);
    }

    /**
     * Transform byte array to md5 format(32bit lower case).
     *
     * @param input Byte array expected to be transformed
     * @return String of md5 format
     */
    public static String md5(byte[] input) {
        return md5(input, true, false);
    }

    /**
     * Transform byte array to md5 format(32bit).
     *
     * @param input     Byte array expected to be transformed
     * @param upperCase {@code true} output md5 string of upper case
     * @return String of md5 format
     */
    public static String md5(byte[] input, boolean upperCase) {
        return md5(input, true, upperCase);
    }

    /**
     * Transform string to md5 format.
     *
     * @param str       String expected to be transformed
     * @param is32Bit   {@code true} output 32bit md5 string
     * @param upperCase {@code true} output md5 string of upper case
     * @return String of md5 format
     */
    public static String md5(String str, boolean is32Bit, boolean upperCase) {
        return md5(str.getBytes(), is32Bit, upperCase);
    }

    /**
     * Transform byte array to md5 format.
     *
     * @param input     Byte array expected to be transformed
     * @param is32Bit   {@code true} output 32bit md5 string
     * @param upperCase {@code true} output md5 string of upper case
     * @return String of md5 format
     */
    public static String md5(byte[] input, boolean is32Bit, boolean upperCase) {
        char[] digits = (upperCase ? upperDigits : lowDigits);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] res = md.digest(input);
            char[] chs = new char[res.length << 1];
            for (int i = 0; i < res.length; i++) {
                int index = i << 1;
                chs[index + 1] = digits[res[i] & 0B1111];
                chs[index] = digits[(res[i] & 0B11110000) >> 4];
            }

            if (is32Bit) {
                return new String(chs);
            } else {
                return new String(chs, 8, 24);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to compute MD5 hash", e);
        }
    }


}
