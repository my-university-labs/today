package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-21.
 */

public class TodayWeatherBean extends WeatherBean {
    private TodayWeatherFromJsonBean.Results.Location location;
    private TodayWeatherFromJsonBean.Results.Now now;
    private FutureWeatherFromJsonBean.Results.Daily today;
    private String last_update;

    public TodayWeatherBean(TodayWeatherFromJsonBean twjson, FutureWeatherFromJsonBean fwjson) {
        try {
            location = twjson.getResults().get(0).getLocation();
            now = twjson.getResults().get(0).getNow();
            today = fwjson.getResults().get(0).getDaily().get(0);
            last_update = twjson.getResults().get(0).getLast_update();
        } catch (Exception e) {

        }
    }

    public TodayWeatherFromJsonBean.Results.Location getLocation() {
        return location;
    }

    public void setLocation(TodayWeatherFromJsonBean.Results.Location location) {
        this.location = location;
    }

    public TodayWeatherFromJsonBean.Results.Now getNow() {
        return now;
    }

    public void setNow(TodayWeatherFromJsonBean.Results.Now now) {
        this.now = now;
    }

    public FutureWeatherFromJsonBean.Results.Daily getToday() {
        return today;
    }

    public void setToday(FutureWeatherFromJsonBean.Results.Daily today) {
        this.today = today;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
