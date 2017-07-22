package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-21.
 */

public class TodayWeatherEntity extends WeatherEntity {
    private TodayWeatherFromJsonEntity.Results.Location location;
    private TodayWeatherFromJsonEntity.Results.Now now;
    private FutureWeatherFromJsonEntity.Results.Daily today;
    private String last_update;

    public TodayWeatherEntity(TodayWeatherFromJsonEntity twjson, FutureWeatherFromJsonEntity fwjson) {
        location = twjson.getResults().get(0).getLocation();
        now = twjson.getResults().get(0).getNow();
        today = fwjson.getResults().get(0).getDaily().get(0);
        last_update = twjson.getResults().get(0).getLast_update();
    }

    public TodayWeatherFromJsonEntity.Results.Location getLocation() {
        return location;
    }

    public void setLocation(TodayWeatherFromJsonEntity.Results.Location location) {
        this.location = location;
    }

    public TodayWeatherFromJsonEntity.Results.Now getNow() {
        return now;
    }

    public void setNow(TodayWeatherFromJsonEntity.Results.Now now) {
        this.now = now;
    }

    public FutureWeatherFromJsonEntity.Results.Daily getToday() {
        return today;
    }

    public void setToday(FutureWeatherFromJsonEntity.Results.Daily today) {
        this.today = today;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
