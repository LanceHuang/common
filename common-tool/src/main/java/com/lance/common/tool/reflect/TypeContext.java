package com.lance.common.tool.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型上下文
 *
 * @author Lance
 */
public class TypeContext {

    /** 泛型 -> 绑定类型 */
    private Map<Type, Type> bindTypeMapping = new HashMap<>();

    public TypeContext(Type[] formalArgs, Type[] actualArgs) {
        for (int i = 0; i < formalArgs.length; i++) {
            bindTypeMapping.put(formalArgs[i], actualArgs[i]);
        }
    }

    /**
     * 解析类上下文
     */
    public static TypeContext parseContext(Class<?> javaClass) {
        // TODO: 2020/11/10 多级继承怎么处理？
        Type[] formalArgs = javaClass.getSuperclass().getTypeParameters();
        Type genericSuperclass = javaClass.getGenericSuperclass();
        Type[] actualArgs;
        if (genericSuperclass instanceof ParameterizedType) {
            actualArgs = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        } else {
            actualArgs = new Type[0];
        }
        // TODO: 2020/11/11 看能不能找到endPoint
        return new TypeContext(formalArgs, actualArgs);
    }

    /**
     * 获取绑定类型
     *
     * @param genericType 泛型
     */
    public Type bind(Type genericType) {
        Type bindType = bindTypeMapping.get(genericType);
        return bindType == null ? genericType : bindType;
    }

}
