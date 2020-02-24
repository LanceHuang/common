package com.lance.common.tool.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Json tool
 *
 * @author Lance
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    private static TypeFactory typeFactory = TypeFactory.defaultInstance();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JsonUtils() {
    }


    /**
     * Transform object to json.
     */
    public static String object2json(Object obj) throws Exception {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new Exception("Failed to parse object.", e);
        }
    }

    /**
     * Transform json to object.
     *
     * @param str String of json format
     */
    public static <T> T json2object(String str, Class<T> clazz) throws Exception {
        try {
            return mapper.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            throw new Exception("Failed to parse json.", e);
        }
    }

    /**
     * Transform map to json.
     */
    public static <K, V> String map2json(Map<K, V> data) throws Exception {
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new Exception("Failed to parse map.", e);
        }
    }

    /**
     * Transform json to map.
     *
     * @param str String of json format
     */
    public static Map<String, Object> json2map(String str) throws Exception {
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Object.class);
        try {
            return mapper.readValue(str, mapType);
        } catch (JsonProcessingException e) {
            throw new Exception("Failed to parse json.", e);
        }
    }

    /**
     * Transform json to map.
     *
     * @param str String of json format
     */
    public static <K, V> Map<K, V> json2map(String str, Class<K> kClass, Class<V> vClass) throws Exception {
        MapType mapType = typeFactory.constructMapType(HashMap.class, kClass, vClass);
        try {
            return mapper.readValue(str, mapType);
        } catch (JsonProcessingException e) {
            throw new Exception("Failed to parse json.", e);
        }
    }

}
