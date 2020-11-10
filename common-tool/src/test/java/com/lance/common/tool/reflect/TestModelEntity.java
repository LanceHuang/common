package com.lance.common.tool.reflect;

/**
 * 测试实体
 *
 * @author Lance
 */
public class TestModelEntity extends AbstractModelEntity<Long, String> {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
