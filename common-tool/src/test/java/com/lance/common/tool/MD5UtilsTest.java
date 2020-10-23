package com.lance.common.tool;

import com.lance.common.tool.MD5Utils;
import org.junit.Test;

/**
 * Created by Lance on 2016/8/12.
 */
public class MD5UtilsTest {

    @Test
    public void test() {
        System.out.println(MD5Utils.md5("Lance", false, false));
        System.out.println(MD5Utils.md5("Lance", false, true));
        System.out.println(MD5Utils.md5("Lance", true, false));
        System.out.println(MD5Utils.md5("Lance", true, true));
    }
}
