package pers.dongchangzhang.todayisbeautiful.entity;

import android.content.Context;

import pers.dongchangzhang.todayisbeautiful.utils.Tools;

/**
 * Created by cc on 17-7-19.
 */

public class WeatherEntity {
    private int status;
    private String last_update;
    private String temp;
    private String path;
    private String describe;
    private String minTemp;
    private String maxTemp;

    private int status_id;

    public WeatherEntity(Context context, TodayWeatherEntity tw) {
        this.status = Tools.getImageByReflect(context, "w" + tw.getCode());
        this.last_update = tw.getLast_update();
        this.temp = tw.getTemperature();
        this.path = tw.getPath();
        this.describe = tw.getText();
    }
    public WeatherEntity(int status, String time, String describe, String minTemp, String maxTemp) {
        this.status = status;
        this.last_update = time;
        this.describe = describe;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }


    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
