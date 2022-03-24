package com.lance.common.tool;

import org.springframework.context.SmartLifecycle;

/**
 * 生命周期基类
 *
 * @author Lance
 * @since 2021/11/29
 */
public abstract class LifecycleBase implements SmartLifecycle {

    private volatile boolean running = false;

    @Override
    public void start() {
        startInternal();
        running = true;
    }

    /**
     * 启动处理
     */
    public abstract void startInternal();

    @Override
    public void stop() {
        stopInternal();
        running = false;
    }

    /**
     * 关闭处理
     */
    public abstract void stopInternal();

    @Override
    public boolean isRunning() {
        return running;
    }
}
