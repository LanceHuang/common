package com.lance.common.tool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Singleton resource manager, used for auto-release resource. Usage:
 * <pre>
 *     ResourceManager.addResource(new AutoCloseableResource());
 * </pre>
 *
 * @author Lance
 * @since 2017/3/26
 */
public final class ResourceManager {

    private static Logger LOG = LoggerFactory.getLogger(ResourceManager.class);
    private static List<AutoCloseable> RESOURCES = new LinkedList<>();

    //添加钩子
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                ResourceManager.close();
            }
        });
    }

    private ResourceManager() {

    }

    /**
     * Add resources which implements <code>AutoCloseable</code>.
     */
    public static void addResource(AutoCloseable resource) {
        Assert.assertNotNull(resource, "Resource can't be null");
        RESOURCES.add(resource);
    }

    /**
     * Release all resources.
     */
    public static void close() {
        LOG.info("Release all resources, size: " + RESOURCES.size());
        for (AutoCloseable item : RESOURCES) {
            try {
                item.close();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}