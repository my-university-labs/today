package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-22.
 */

public class MoreWeatherInfoEntity extends WeatherEntity {
    private LifeInfoFromJsonEntity.Results.Suggestion.Uv uv;
    private LifeInfoFromJsonEntity.Results.Suggestion.Dressing dressing;
    private FutureWeatherFromJsonEntity.Results.Daily today;
    private TodayWeatherFromJsonEntity.Results.Now now;
    public MoreWeatherInfoEntity(TodayWeatherEntity tw, LifeInfoEntity li) {
        this.uv = li.getUv();
        this.dressing = li.getDressing();
        this.now = tw.getNow();
        this.today = tw.getToday();
    }

    public LifeInfoFromJsonEntity.Results.Suggestion.Uv getUv() {
        return uv;
    }

    public void setUv(LifeInfoFromJsonEntity.Results.Suggestion.Uv uv) {
        this.uv = uv;
    }

    public LifeInfoFromJsonEntity.Results.Suggestion.Dressing getDressing() {
        return dressing;
    }

    public void setDressing(LifeInfoFromJsonEntity.Results.Suggestion.Dressing dressing) {
        this.dressing = dressing;
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
}
