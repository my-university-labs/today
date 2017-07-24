package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-19.
 */

public class CityBean {
    private String id;
    private String name;
    private String parent_id;
    public CityBean() {}
    public CityBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public CityBean(String id, String name, String parent_id) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
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

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
