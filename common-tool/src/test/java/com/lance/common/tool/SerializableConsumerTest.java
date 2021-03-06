package com.lance.common.tool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * @author Lance
 * @since 2020/12/31
 */
public class SerializableConsumerTest {

    @Test
    public void test() throws NoSuchMethodException {
        SerializableConsumer<String> consumer = this::sop;
        Method m = consumer.getClass().getDeclaredMethod("writeReplace");
        Assertions.assertNotNull(m);
    }

    private void sop(String message) {
        // do sth
    }
}