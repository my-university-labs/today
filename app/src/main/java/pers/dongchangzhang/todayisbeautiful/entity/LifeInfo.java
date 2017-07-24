package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-22.
 */

public class LifeInfo {
    private LifeInfoFromJson.Results.Suggestion.Uv uv;
    private LifeInfoFromJson.Results.Suggestion.Dressing dressing;

    public LifeInfo(LifeInfoFromJson lijson) {
        uv = lijson.getResults().get(0).getSuggestion().getUv();
        dressing = lijson.getResults().get(0).getSuggestion().getDressing();
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
}
