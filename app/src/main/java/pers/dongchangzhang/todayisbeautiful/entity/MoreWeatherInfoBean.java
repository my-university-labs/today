package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-22.
 */

public class MoreWeatherInfoBean extends WeatherBean {
    private LifeInfoFromJsonBean.Results.Suggestion.Uv uv;
    private LifeInfoFromJsonBean.Results.Suggestion.Dressing dressing;
    private FutureWeatherFromJsonBean.Results.Daily today;
    private TodayWeatherFromJsonBean.Results.Now now;
    public MoreWeatherInfoBean(TodayWeatherBean tw, LifeInfoBean li) {
        this.uv = li.getUv();
        this.dressing = li.getDressing();
        this.now = tw.getNow();
        this.today = tw.getToday();
    }

    public LifeInfoFromJsonBean.Results.Suggestion.Uv getUv() {
        return uv;
    }

    public void setUv(LifeInfoFromJsonBean.Results.Suggestion.Uv uv) {
        this.uv = uv;
    }

    public LifeInfoFromJsonBean.Results.Suggestion.Dressing getDressing() {
        return dressing;
    }

    public void setDressing(LifeInfoFromJsonBean.Results.Suggestion.Dressing dressing) {
        this.dressing = dressing;
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
}
