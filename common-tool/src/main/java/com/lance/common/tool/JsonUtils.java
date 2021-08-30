package com.lance.common.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * json工具类
 *
 * @author Lance
 */
public final class JsonUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final TypeFactory TYPE_FACTORY = TypeFactory.defaultInstance();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    private JsonUtils() {
    }

    /**
     * Transform object to json.
     */
    public static String object2json(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOG.error("Failed to parse object.", e);
        }
        return null;
    }

    /**
     * Transform json to object.
     *
     * @param str String of json format
     */
    public static <T> T json2object(String str, Class<T> clazz) {
        try {
            return MAPPER.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            LOG.error("Failed to parse json.", e);
        }
        return null;
    }

    /**
     * Transform map to json.
     */
    public static <K, V> String map2json(Map<K, V> data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            LOG.error("Failed to parse map.", e);
        }
        return null;
    }

    /**
     * Transform json to map.
     *
     * @param str String of json format
     */
    public static Map<String, Object> json2map(String str) {
        MapType mapType = TYPE_FACTORY.constructMapType(HashMap.class, String.class, Object.class);
        try {
            return MAPPER.readValue(str, mapType);
        } catch (JsonProcessingException e) {
            LOG.error("Failed to parse json.", e);
        }
        return null;
    }

    /**
     * Transform json to map.
     *
     * @param str String of json format
     */
    public static <K, V> Map<K, V> json2map(String str, Class<K> kClass, Class<V> vClass) {
        MapType mapType = TYPE_FACTORY.constructMapType(HashMap.class, kClass, vClass);
        try {
            return MAPPER.readValue(str, mapType);
        } catch (JsonProcessingException e) {
            LOG.error("Failed to parse json.", e);
        }
        return null;
    }

}
