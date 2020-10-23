package com.lance.common.tool.util;

import com.lance.common.tool.SensitiveWordFilter;
import org.junit.Before;
import org.junit.Test;

public class SensitiveWordFilterTest {

    private String message = "What the fuck!!! Shit it !";

    private String locations = "sensitive-word-dict.txt";

    private SensitiveWordFilter filter;

    @Before
    public void before() {
        filter = new SensitiveWordFilter(locations);
    }

    @Test
    public void filter() {
        System.out.println("Pre: " + message);
        System.out.println("Post: " + filter.filter(message));
    }

    @Test
    public void endOfSensitiveWord() {
        System.out.println("Pre: " + message);
        System.out.println("Post: " + filter.endOfSensitiveWord(message, 0));
        System.out.println("Post: " + filter.endOfSensitiveWord(message, 9));
        System.out.println("Post: " + filter.endOfSensitiveWord(message, 10));
    }

    @Test
    public void containSensitiveWord() {
        System.out.println("Pre: " + message);
        System.out.println("Post: " + filter.containSensitiveWord(message));
    }
}