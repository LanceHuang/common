package com.lance.common.tool.util;

import com.lance.common.tool.util.model.Person;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 */
public class JsonUtilsTest {

    @Test
    public void testObject2json() throws Exception {
        Person person = new Person();
        System.out.println(JsonUtils.object2json(person));
        person.setName("Lance");
        System.out.println(JsonUtils.object2json(person));
        person.setAge(123);
        System.out.println(JsonUtils.object2json(person));
        person.setCreateTime(new Date());
        System.out.println(JsonUtils.object2json(person));

        System.out.println(JsonUtils.object2json(null));
    }

    @Test
    public void testJson2object() throws Exception {
        String strJson = "{\"name\":null,\"age\":0}";
        System.out.println(JsonUtils.json2object(strJson, Person.class));
        strJson = " {\"name\":\"Lance\",\"age\":0}";
        System.out.println(JsonUtils.json2object(strJson, Person.class));
        strJson = "{\"name\":\"Lance\",\"age\":123}";
        System.out.println(JsonUtils.json2object(strJson, Person.class));
        strJson = "{\"name\":\"Lance\",\"age\":123,\"createTime\":1582531462532}";
        System.out.println(JsonUtils.json2object(strJson, Person.class));

        strJson = "{\"name\":\"Lance\",\"age\":123,\"nickname\":\"Leo\"}";
        System.out.println(JsonUtils.json2object(strJson, Person.class));
        strJson = "{\"name\":\"Lance\"}";
        System.out.println(JsonUtils.json2object(strJson, Person.class));

        System.out.println(JsonUtils.json2object(null, Person.class));
    }

    @Test
    public void testMap2json() throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("name", "Lance");
        data.put("career", "Programmer");
        System.out.println(JsonUtils.map2json(data));

        Map<String, Person> data2 = new HashMap<>();
        Person person = new Person();
        person.setName("Lance");
        data2.put("account1", person);
        data2.put("account2", new Person());
        System.out.println(JsonUtils.map2json(data2));
    }

    @Test
    public void testJson2Map() throws Exception {
        String testJson = "{\"career\":\"Programmer\",\"name\":\"Lance\"}";
        System.out.println(JsonUtils.json2map(testJson));
        String testJson2 = "{\"account2\":{\"name\":null,\"age\":0,\"createTime\":null},\"account1\":{\"name\":\"Lance\",\"age\":0,\"createTime\":null}}";
        System.out.println(JsonUtils.json2map(testJson2, String.class, Person.class));
    }

}

