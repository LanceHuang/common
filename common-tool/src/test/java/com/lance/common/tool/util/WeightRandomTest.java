package com.lance.common.tool.util;

import com.lance.common.tool.WeightRandom;
import org.junit.Test;

public class WeightRandomTest {

    @Test
    public void test() {
        WeightRandom<String> weightRandom = new WeightRandom<>();
        weightRandom.add(10, "Hello");
        weightRandom.add(30, "World");
        weightRandom.add(50, "Lance");
        System.out.println(weightRandom.next());
        System.out.println(weightRandom.next());
        System.out.println(weightRandom.next());
    }

    @Test
    public void testEmpty() {
        WeightRandom<String> weightRandom = new WeightRandom<>();
        System.out.println(weightRandom.next());
    }

    @Test
    public void testNullWeight() {
        WeightRandom<String> weightRandom = new WeightRandom<>();
        Integer weight = null;
        weightRandom.add(weight, "Empty");
    }

    @Test
    public void testNegativeWeight() {
        WeightRandom<String> weightRandom = new WeightRandom<>();
        weightRandom.add(-1, "Empty");
    }


    @Test
    public void testNullOutput() {
        WeightRandom<String> weightRandom = new WeightRandom<>();
        weightRandom.add(90, null);
    }

}