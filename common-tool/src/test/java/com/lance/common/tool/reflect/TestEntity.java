package com.lance.common.tool.reflect;

/**
 * 测试实体
 *
 * @author Lance
 */
public class TestEntity extends AbstractEntity<Long> {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
