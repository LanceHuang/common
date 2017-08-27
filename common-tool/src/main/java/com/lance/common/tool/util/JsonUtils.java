package com.lance.common.tool.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Json tool
 *
 * @author Lance
 * @date 2016/10/21 21:39
 */
public class JsonUtils {
    private JsonUtils() {
    }

    /**
     * Transform string of json format to map.
     *
     * @param jsonStr String of json format
     * @return {@code Map} map result, or {@code null}
     * @throws JSONException Failed to analyze string of json format.
     */
    public static Map<String, String> json2map(String jsonStr) throws JSONException {
        Map<String, String> result = new HashMap<String, String>();

        JSONObject jsonObject = new JSONObject(jsonStr);
        Iterator iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = jsonObject.getString(key);
            result.put(key, value);
        }

        return result;
    }
}
