package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-19.
 */

public class CityEntity {
    private String id;
    private String name;
    public CityEntity() {}
    public CityEntity(String id, String name) {
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
