package com.lance.common.test;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * lambda测试用例，参考 https://www.javacodegeeks.com/2016/07/junit-5-dynamic-tests.html
 *
 * @author Lance
 * @since 2020/12/31
 */
public class LambdaTestCase {

    /** 一组测试用例 */
    private final List<DynamicTest> tests = new LinkedList<>();

    /**
     * 执行测试用例
     *
     * @param displayName 测试用例名
     * @param executable  执行逻辑
     */
    protected void execute(String displayName, Executable executable) {
        if (displayName == null || displayName.isEmpty()) {
            throw new IllegalArgumentException("displayName is empty");
        }
        if (executable == null) {
            throw new IllegalArgumentException("executable is null");
        }
        tests.add(DynamicTest.dynamicTest(displayName, executable));
    }

    @TestFactory
    public Iterator<DynamicTest> testFactory() {
        return tests.listIterator();
    }
}

