package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-21.
 */

public class TodayWeather extends Weather {
    private TodayWeatherFromJson.Results.Location location;
    private TodayWeatherFromJson.Results.Now now;
    private FutureWeatherFromJson.Results.Daily today;
    private String last_update;

    public TodayWeather(TodayWeatherFromJson twjson, FutureWeatherFromJson fwjson) {
        try {
            location = twjson.getResults().get(0).getLocation();
            now = twjson.getResults().get(0).getNow();
            today = fwjson.getResults().get(0).getDaily().get(0);
            last_update = twjson.getResults().get(0).getLast_update();
        } catch (Exception e) {

        }
    }

    public TodayWeatherFromJson.Results.Location getLocation() {
        return location;
    }

    public void setLocation(TodayWeatherFromJson.Results.Location location) {
        this.location = location;
    }

    public TodayWeatherFromJson.Results.Now getNow() {
        return now;
    }

    public void setNow(TodayWeatherFromJson.Results.Now now) {
        this.now = now;
    }

    public FutureWeatherFromJson.Results.Daily getToday() {
        return today;
    }

    public void setToday(FutureWeatherFromJson.Results.Daily today) {
        this.today = today;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
