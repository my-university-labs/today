package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-22.
 */

public class FutureWeather extends Weather {
    private FutureWeatherFromJson.Results.Location location;
    private FutureWeatherFromJson.Results.Daily daily;
    private String last_update;

    public FutureWeather(FutureWeatherFromJson.Results.Location loc, FutureWeatherFromJson.Results.Daily da, String last_update) {
        this.location = loc;
        this.daily = da;
        this.last_update = last_update;
    }

    public FutureWeatherFromJson.Results.Location getLocation() {
        return location;
    }

    public void setLocation(FutureWeatherFromJson.Results.Location location) {
        this.location = location;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public FutureWeatherFromJson.Results.Daily getDaily() {
        return daily;
    }

    public void setDaily(FutureWeatherFromJson.Results.Daily daily) {
        this.daily = daily;
    }
}
