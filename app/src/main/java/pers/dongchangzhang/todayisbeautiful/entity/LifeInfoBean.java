package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-22.
 */

public class LifeInfoBean {
    private LifeInfoFromJsonBean.Results.Suggestion.Uv uv;
    private LifeInfoFromJsonBean.Results.Suggestion.Dressing dressing;
    public LifeInfoBean(LifeInfoFromJsonBean lijson) {
        uv = lijson.getResults().get(0).getSuggestion().getUv();
        dressing = lijson.getResults().get(0).getSuggestion().getDressing();
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
}
