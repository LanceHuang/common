package com.lance.common.tool.util;

import org.junit.Test;

import static com.lance.common.tool.util.NumberUtils.*;

/**
 * @author Lance
 */
public class NumberUtilsTest {

    @Test
    public void test() {
        System.out.println(byteArrayToLong(longToByteArray(10)));
        System.out.println(byteArrayToLong(longToByteArray(-10)));
        System.out.println(byteArrayToLong(longToByteArray(12345678L)));
        System.out.println(byteArrayToLong(longToByteArray(-12345678L)));
        System.out.println(byteArrayToLong(longToByteArray(Long.MAX_VALUE)));
        System.out.println(byteArrayToLong(longToByteArray(Long.MIN_VALUE)));
    }
}