package com.lance.common.tool.reflect;

import org.junit.Test;

/**
 * @author Lance
 */
public class TClassTest {

    @Test
    public void testInheritance() {
        TClass tClass = TClass.toTClass(TestEntity.class);
        for (TField f : tClass.getFields()) {
            System.out.println(f.getJavaField().getName() + " " + f.getBindType().getTypeName());
        }
        // TODO: 2020/11/10 怎么处理List<T>？
    }

    @Test
    public void testMultipleInheritance() {
        TClass tClass = TClass.toTClass(TestModelEntity.class);
        for (TField f : tClass.getFields()) {
            System.out.println(f.getJavaField().getName() + " " + f.getBindType().getTypeName());
        }
    }
}
