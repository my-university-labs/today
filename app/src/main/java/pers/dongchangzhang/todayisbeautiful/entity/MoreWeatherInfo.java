package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-22.
 */

public class MoreWeatherInfo extends Weather {
    private LifeInfoFromJson.Results.Suggestion.Uv uv;
    private LifeInfoFromJson.Results.Suggestion.Dressing dressing;
    private FutureWeatherFromJson.Results.Daily today;
    private TodayWeatherFromJson.Results.Now now;

    public MoreWeatherInfo(TodayWeather tw, LifeInfo li) {
        this.uv = li.getUv();
        this.dressing = li.getDressing();
        this.now = tw.getNow();
        this.today = tw.getToday();
    }

    public LifeInfoFromJson.Results.Suggestion.Uv getUv() {
        return uv;
    }

    public void setUv(LifeInfoFromJson.Results.Suggestion.Uv uv) {
        this.uv = uv;
    }

    public LifeInfoFromJson.Results.Suggestion.Dressing getDressing() {
        return dressing;
    }

    public void setDressing(LifeInfoFromJson.Results.Suggestion.Dressing dressing) {
        this.dressing = dressing;
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
}
