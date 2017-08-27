package com.lance.common.tool.util;

/**
 * Assertion.It's mainly used for formatting message of assertion.
 *
 * @author Lance
 * @date 2016/10/27 18:04
 */
public class Assert {

    /**
     * Assert that object isn't {@code null}
     *
     * @param obj       expected object
     * @param msgFormat message format
     * @param params    parameters
     * @throws IllegalArgumentException object is {@code null}
     */
    public static void assertNotNull(Object obj, String msgFormat, Object... params) {
        if (null == obj)
            throw new IllegalArgumentException(null == msgFormat ? null : String.format(msgFormat, params));
    }

    /**
     * Assert that object isn't {@code null} and empty
     *
     * @param str       expected string
     * @param msgFormat message format
     * @param params    parameters
     * @throws IllegalArgumentException object is {@code null}
     */
    public static void assertNotNullOrEmpty(String str, String msgFormat, Object... params) {
        if (null == str || str.isEmpty())
            throw new IllegalArgumentException(null == msgFormat ? null : String.format(msgFormat, params));
    }

    /**
     * Assert that {@code num1>num2}
     *
     * @param num1      expected bigger one
     * @param num2      expected smaller one
     * @param msgFormat message format
     * @param params    parameters
     * @throws IllegalArgumentException {@code num1<=num2}
     */
    public static void assertGt(int num1, int num2, String msgFormat, Object... params) {
        if (num1 <= num2)
            throw new IllegalArgumentException(null == msgFormat ? null : String.format(msgFormat, params));
    }

    /**
     * Assert that {@code num1>=num2}
     *
     * @param num1      expected bigger one
     * @param num2      expected smaller one
     * @param msgFormat message format
     * @param params    parameters
     * @throws IllegalArgumentException {@code num1<num2}
     */
    public static void assertGe(int num1, int num2, String msgFormat, Object... params) {
        if (num1 < num2)
            throw new IllegalArgumentException(null == msgFormat ? null : String.format(msgFormat, params));
    }

    /**
     * Assert that {@code num1<num2}
     *
     * @param num1      expected smaller one
     * @param num2      expected bigger one
     * @param msgFormat message format
     * @param params    parameters
     * @throws IllegalArgumentException {@code num1>=num2}
     */
    public static void assertLt(int num1, int num2, String msgFormat, Object... params) {
        if (num1 > num2)
            throw new IllegalArgumentException(null == msgFormat ? null : String.format(msgFormat, params));
    }

    /**
     * Assert that {@code num1>=num2}
     *
     * @param num1      expected smaller one
     * @param num2      expected bigger one
     * @param msgFormat message format
     * @param params    parameters
     * @throws IllegalArgumentException {@code num1>num2}
     */
    public static void assertLe(int num1, int num2, String msgFormat, Object... params) {
        if (num1 < num2)
            throw new IllegalArgumentException(null == msgFormat ? null : String.format(msgFormat, params));
    }

    /**
     * Assert that obj1 is equals to obj2
     *
     * @param obj1 expected object
     * @param obj2 expected object
     * @throws IllegalArgumentException obj1 is not equals to obj2
     */
    public static void assertEquals(Object obj1, Object obj2) {
        assertEquals(obj1, obj2, "Obj1 is not equals to obj2");
    }

    /**
     * Assert that obj1 is equals to obj2
     *
     * @param obj1      expected object
     * @param obj2      expected object
     * @param msgFormat message format
     * @param params    parameters
     * @throws IllegalArgumentException obj1 is not equals to obj2
     */
    public static void assertEquals(Object obj1, Object obj2, String msgFormat, Object... params) {
        String msg = (null == msgFormat ? null : String.format(msgFormat, params));
        if (null == obj1 && obj2 != null)
            throw new IllegalArgumentException(msg);
        if (obj1 == obj2)
            return;
        if (!obj1.equals(obj2))
            throw new IllegalArgumentException(msg);
    }

}
