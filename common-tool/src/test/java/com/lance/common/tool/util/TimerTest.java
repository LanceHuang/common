package com.lance.common.tool.util;

import org.junit.Test;

/**
 * @author Lance
 * @date 2016/11/6
 */
public class TimerTest {

    @Test
    public void test() {
        Timer.time();
        int num1 = 10000051/2;
        Timer.time();
        System.out.println("interval:" + Timer.interval());

        Timer.time();
        int num2 = 10000051%2;
        Timer.time();
        System.out.println("interval:" + Timer.interval());
    }

}