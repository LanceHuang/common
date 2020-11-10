package com.lance.common.tool.reflect;

/**
 * @author Lance
 */
public abstract class AbstractEntity<T> implements Entity<T> {

    private T id;

    @Override
    public T getId() {
        return this.id;
    }

    @Override
    public void setId(T id) {
        this.id = id;
    }
}
