package com.lance.common.test;

/**
 * @author Lance
 * @since 2020/12/31
 */
public class LambdaTestCaseTest extends LambdaTestCase {
    {
        // 测试1
        execute("test", () -> System.out.println("Hello world"));

        // 测试2
        execute("test2", () -> System.out.println("Hello world"));
    }
}