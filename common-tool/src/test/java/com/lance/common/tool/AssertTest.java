package com.lance.common.tool;

import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @date 2016/10/27 18:10
 */
public class AssertTest {

    @Test
    public void testAssertNotNull() throws Exception {
        Assert.assertNotNull("", "[error] %s", "null");
        Assert.assertNotNull(null, "[error] %s", "null");
        Assert.assertNotNull("", "[error] %s", null);
        Assert.assertNotNull("", "[error]");
        Assert.assertNotNull("", null, null);
        Assert.assertNotNull("", null);
        Assert.assertNotNull(null, null, null);
    }

}