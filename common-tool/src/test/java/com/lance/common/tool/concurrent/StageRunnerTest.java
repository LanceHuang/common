package com.lance.common.tool.concurrent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance
 * @since 2022/3/24
 */
class StageRunnerTest {

    @Test
    void test() {
        new Thread(decorate(() -> System.out.println("Hello world"))).start();
    }

    private Runnable decorate(Runnable runnable) {
        return new StageRunner(runnable, new TaskStage() {
            @Override
            public void handleBefore(Runnable runnable) {
                System.out.println("handleBefore");
            }

            @Override
            public void handleAfter(Runnable runnable) {
                System.out.println("handleAfter");
            }

            @Override
            public void handleError(Runnable runnable, Throwable t) {
                System.out.println("handleError");
            }

            @Override
            public void handleFinal(Runnable runnable) {
                System.out.println("handleFinal");
            }
        });
    }
}