package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-22.
 */

public class LifeInfoEntity {
    private LifeInfoFromJsonEntity.Results.Suggestion.Uv uv;
    private LifeInfoFromJsonEntity.Results.Suggestion.Dressing dressing;
    public LifeInfoEntity(LifeInfoFromJsonEntity lijson) {
        uv = lijson.getResults().get(0).getSuggestion().getUv();
        dressing = lijson.getResults().get(0).getSuggestion().getDressing();
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
}
