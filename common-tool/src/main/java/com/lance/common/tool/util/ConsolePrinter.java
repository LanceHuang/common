package com.lance.common.tool.util;

/**
 * DSL of printer.
 * @author Lance
 * @since 2017/3/10
 */
public class ConsolePrinter {
    private ConsolePrinter() {
    }

    public static void println() {
        System.out.println();
    }

    public static <T> void println(T t) {
        System.out.println(t);
    }

    public static <T> void print(T t) {
        System.out.print(t);
    }

    public static void printf(String format, Object... obj) {
        System.out.printf(format, obj);
    }

}
