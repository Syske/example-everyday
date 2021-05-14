package io.github.syske.example20210513;

/**
 * @program: example-2021.05.12
 * @description:
 * @author: syske
 * @create: 2021-05-13 07:21
 */
public class ChildEntriy {
    private Long id;
    private String name;

    public ChildEntriy() {
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

    @Override
    public String toString() {
        return "ChildEntriy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
