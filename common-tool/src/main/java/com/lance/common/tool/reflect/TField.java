package com.lance.common.tool.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * @author Lance
 */
public class TField {

    /** java成员变量 */
    private Field javaField;

    /** 绑定类型 */
    private Type bindType;

    public TField(Field javaField, Type bindType) {
        this.javaField = javaField;
        this.bindType = bindType;
    }

    public Field getJavaField() {
        return javaField;
    }

    public Type getBindType() {
        return bindType;
    }
}
