package pers.dongchangzhang.todayisbeautiful.todayisbeautiful.entity;

import android.graphics.drawable.Drawable;
import android.util.Log;

import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.utils.Tools;

/**
 * Created by cc on 17-7-19.
 */

public class Weather {
    private int status;
    private String time;
    private String describe;
    private String minTemp;
    private String maxTemp;

    private int status_id;

    public Weather(int status, String time, String describe, String minTemp, String maxTemp) {
        this.status = status;
        this.time = time;
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

    public String getTime() {
        return time;
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

    public void setTime(String time) {
        this.time = time;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

}
