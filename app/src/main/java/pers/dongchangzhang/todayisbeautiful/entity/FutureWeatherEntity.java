package pers.dongchangzhang.todayisbeautiful.entity;

import java.util.List;

/**
 * Created by cc on 17-7-22.
 */

public class FutureWeatherEntity extends WeatherEntity {
    private FutureWeatherFromJsonEntity.Results.Location location;
    private FutureWeatherFromJsonEntity.Results.Daily daily;
    private String last_update;

    public FutureWeatherEntity(FutureWeatherFromJsonEntity.Results.Location loc, FutureWeatherFromJsonEntity.Results.Daily da, String last_update) {
        this.location = loc;
        this.daily = da;
        this.last_update = last_update;
    }

    public FutureWeatherFromJsonEntity.Results.Location getLocation() {
        return location;
    }

    public void setLocation(FutureWeatherFromJsonEntity.Results.Location location) {
        this.location = location;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public FutureWeatherFromJsonEntity.Results.Daily getDaily() {
        return daily;
    }

    public void setDaily(FutureWeatherFromJsonEntity.Results.Daily daily) {
        this.daily = daily;
    }
}
