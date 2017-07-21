package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-21.
 */

public class TodayWeatherEntity {
    private String name;
    private String country;
    private String path;
    private String text;
    private String code;
    private String temperature;
    private String last_update;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTemperature() {
        return this.temperature + "℃";
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
