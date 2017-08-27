package com.lance.common.tool.util;

import org.junit.Test;

import java.util.Map;

/**
 * @author Lance
 * @date 2016/10/26 11:33
 */
public class JsonUtilsTest {

    @Test
    public void testJson2map() throws Exception {
        Map<String, String> map = JsonUtils.json2map("{\"{\\\"col\\\":\\\"啊\\\",\\\"keyword\\\":\\\"撒\\\"}\":\"match\"}\n");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key：" + entry.getKey());
            System.out.println("value：" + entry.getValue());
        }
    }
}