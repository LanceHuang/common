package com.lance.common.tool.util;

import org.junit.Test;

public class TimeUtilsTest {

    @Test
    public void testFormat() {
        System.out.println(TimeUtils.format(TimeUtils.now()));
    }
}
