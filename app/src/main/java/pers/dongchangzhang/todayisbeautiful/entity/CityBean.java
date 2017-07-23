package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-19.
 */

public class CityBean {
    private String id;
    private String name;
    public CityBean() {}
    public CityBean(String id, String name) {
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
}
