package com.lance.common.tool.util;

/**
 * @author Lance
 * @since 2018-9-27 11:59:21
 */
public final class Math {
    private Math() {
        throw new UnsupportedOperationException();
    }

    /**
     * Refer to netty-all-4.0.23.Final, MultithreadEventExecutorGroup#isPowerOfTwo(int)
     *
     * @param val a value
     * @return {@code true} val is power of two
     */
    public static boolean isPowerOfTwo(int val) {
        return (val & -val) == val;
    }

}
