package com.lance.common.tool.util;

import com.lance.common.tool.TimeUtils;
import org.junit.Test;

public class TimeUtilsTest {

    @Test
    public void testFormat() {
        System.out.println(TimeUtils.format(TimeUtils.now()));
    }
}
