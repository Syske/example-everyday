/* Copyright © 2021 syske. All rights reserved. */
package io.github.syske.example.example20210706;

/**
 * 测试vo
 *
 * @author syske
 * @version 1.0
 * @date 2021-07-06 8:09
 */
public class TestVo {
    private String name;
    private Integer age;

    public TestVo() {
    }

    public TestVo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
