package com.lance.common.tool.util;

import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Lance
 * @since 2017/3/26
 */
public class ResourceManagerTest {
    @Test
    public void addResource() throws Exception {
        ResourceManager.addResource(new A());
        ResourceManager.addResource(new B());
        System.out.println("Work");
    }
}

class A implements Closeable {
    @Override
    public void close() throws IOException {
        System.out.println("A close");
    }
}

class B implements Closeable {
    @Override
    public void close() throws IOException {
        System.out.println("B close");
    }
}