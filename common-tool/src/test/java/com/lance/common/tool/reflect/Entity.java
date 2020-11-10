package com.lance.common.tool.reflect;

/**
 * 自定义实体
 *
 * @author Lance
 */
public interface Entity<T> {

    T getId();

    void setId(T id);
}
