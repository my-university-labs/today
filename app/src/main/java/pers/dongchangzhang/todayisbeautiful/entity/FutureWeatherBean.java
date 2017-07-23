package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-22.
 */

public class FutureWeatherBean extends WeatherBean {
    private FutureWeatherFromJsonBean.Results.Location location;
    private FutureWeatherFromJsonBean.Results.Daily daily;
    private String last_update;

    public FutureWeatherBean(FutureWeatherFromJsonBean.Results.Location loc, FutureWeatherFromJsonBean.Results.Daily da, String last_update) {
        this.location = loc;
        this.daily = da;
        this.last_update = last_update;
    }

    public FutureWeatherFromJsonBean.Results.Location getLocation() {
        return location;
    }

    public void setLocation(FutureWeatherFromJsonBean.Results.Location location) {
        this.location = location;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public FutureWeatherFromJsonBean.Results.Daily getDaily() {
        return daily;
    }

    public void setDaily(FutureWeatherFromJsonBean.Results.Daily daily) {
        this.daily = daily;
    }
}
