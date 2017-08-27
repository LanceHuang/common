package com.lance.common.tool.util;

import org.junit.Test;

import static com.lance.common.tool.util.ConsolePrinter.*;

/**
 * @author Lance
 * @since 2017/3/10
 */
public class ConsolePrinterTest {
    @Test
    public void test() {
        println();
        println("Hi");
        print("Hello print\n");
        printf("num=%d\n", 10);
    }
}