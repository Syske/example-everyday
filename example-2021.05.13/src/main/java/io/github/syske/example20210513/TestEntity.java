package io.github.syske.example20210513;

/**
 * @program: example-2021.05.12
 * @description: 测试entity
 * @author: syske
 * @create: 2021-05-13 07:20
 */
public class TestEntity {
    private Long id;
    private String name;
    private Integer age;
    private ChildEntriy childEntriy;

    public TestEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ChildEntriy getChildEntriy() {
        return childEntriy;
    }

    public void setChildEntriy(ChildEntriy childEntriy) {
        this.childEntriy = childEntriy;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", childEntriy=" + childEntriy +
                '}';
    }
}
