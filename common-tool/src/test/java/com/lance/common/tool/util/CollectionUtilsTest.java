package com.lance.common.tool.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Lance
 * @since 2017/3/12
 */
public class CollectionUtilsTest {
    @Test
    public void merge() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(55);
        list.add(9);
        list.add(0);
        System.out.println(CollectionUtils.merge(list, "|"));
    }

}