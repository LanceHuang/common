package com.lance.common.lab;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void lev() {
        String str1 = "xxc";
        String str2 = "xyz";
        System.out.println(StringUtils.lev(str1, str2));
    }

    @Test
    public void trackLev() {
        String str1 = "xxc";
//        String str2 = "xyz";
        String str2 = "";
        StringUtils.trackLev(str1, str2);
    }
}