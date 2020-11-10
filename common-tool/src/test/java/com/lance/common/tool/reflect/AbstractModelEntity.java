package com.lance.common.tool.reflect;

/**
 * @author Lance
 */
public abstract class AbstractModelEntity<I, M> extends AbstractEntity<I> {

    private M model;

    public M getModel() {
        return model;
    }

    public void setModel(M model) {
        this.model = model;
    }
}
