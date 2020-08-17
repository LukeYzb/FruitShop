package manager.domain;

import java.lang.reflect.Field;

public class Obj {
    private String id;
    private String name;

    public Obj() {
    }

    public Obj(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "Obj{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 给已有对象传入String字符串,确定对象属性
     * @param s
     */
    public void putString(String s) {
    }
}
