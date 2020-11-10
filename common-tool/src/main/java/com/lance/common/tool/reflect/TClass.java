package com.lance.common.tool.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lance
 */
public class TClass {

    /** java类 */
    private Class<?> javaClass;

    /** 成员变量 */
    private List<TField> fields = new LinkedList<>();

    // TODO: 2020/11/10 method

    public TClass(Class<?> javaClass) {
        this.javaClass = javaClass;
    }

    /**
     * 将java类解析成TClass
     *
     * @param javaClass java类
     */
    public static TClass toTClass(Class<?> javaClass) {
        TClass tClass = new TClass(javaClass);
        parseTClass(tClass, TypeContext.parseContext(javaClass), javaClass);
        return tClass;
    }

    /**
     * 解析类
     */
    private static void parseTClass(TClass tClass, TypeContext ctx, Class<?> rootClass) {
        if (rootClass == Object.class) {
            return;
        }

        // 处理成员变量
        for (Field f : rootClass.getDeclaredFields()) {
            Type bindType = ctx.bind(f.getGenericType());
            tClass.addField(new TField(f, bindType));
        }

        // 处理父类
        parseTClass(tClass, ctx, rootClass.getSuperclass());
    }

    /**
     * 添加成员变量
     */
    public void addField(TField field) {
        if (field == null) {
            return;
        }
        this.fields.add(field);
    }

    //=============================== Getter =========================

    public Class<?> getJavaClass() {
        return javaClass;
    }

    public List<TField> getFields() {
        return Collections.unmodifiableList(this.fields);
    }
}
