package com.lance.common.tool;

import com.lance.common.tool.model.Person;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ObjectUtilsTest {

    @Test
    public void test() {
        Person person = new Person();
        System.out.println(person);
        Optional.ofNullable("Lance").ifPresent(person::setName);
        System.out.println(person);
        ObjectUtils.ifPresent(null, person::setName);
        System.out.println(person);
        ObjectUtils.ifPresent("Leo", person::setName);
        System.out.println(person);
    }

}