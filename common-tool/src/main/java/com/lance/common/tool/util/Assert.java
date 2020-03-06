package com.lance.common.tool.util;

import java.util.Collection;

/**
 * Assertion.
 *
 * @author Lance
 * @date 2016/10/27 18:04
 */
public class Assert {

    public static void assertNull(Object obj, String msgFormat, Object... params) {
        if (obj != null) {
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
        }
    }

    public static void assertNotNull(Object obj, String msgFormat, Object... params) {
        if (obj == null) {
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
        }
    }

    public static void assertTrue(boolean value, String msgFormat, Object... params) {
        if (!value) {
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
        }
    }

    public static void assertFalse(boolean value, String msgFormat, Object... params) {
        if (value) {
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
        }
    }

    public static void assertGt(int num1, int num2, String msgFormat, Object... params) {
        if (num1 <= num2) {
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
        }
    }

    public static void assertGe(int num1, int num2, String msgFormat, Object... params) {
        if (num1 < num2) {
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
        }
    }

    public static void assertLt(int num1, int num2, String msgFormat, Object... params) {
        if (num1 > num2) {
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
        }
    }

    public static void assertLe(int num1, int num2, String msgFormat, Object... params) {
        if (num1 < num2) {
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
        }
    }

    public static void assertEquals(int num1, int num2, String msgFormat, Object... params) {
        if (num1 != num2) {
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
        }
    }

    public static void assertEquals(Object obj1, Object obj2, String msgFormat, Object... params) {
        if (obj1 == null && obj2 != null)
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
        if (obj1 == obj2)
            return;
        if (!obj1.equals(obj2))
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
    }

    public static void assertNotEmpty(Collection<?> c, String msgFormat, Object... params) {
        if (c == null || c.isEmpty()) {
            throw new IllegalArgumentException(msgFormat == null ? null : String.format(msgFormat, params));
        }
    }

    public static void assertNotEmpty(String str, String msgFormat, Object... params) {
        if (null == str || str.isEmpty()) {
            throw new IllegalArgumentException(null == msgFormat ? null : String.format(msgFormat, params));
        }
    }

}
