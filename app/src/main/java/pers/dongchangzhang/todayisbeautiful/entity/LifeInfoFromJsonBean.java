package pers.dongchangzhang.todayisbeautiful.entity;

import java.util.List;

/**
 * Created by cc on 17-7-22.
 */

public class LifeInfoFromJsonBean {
    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public class Results {
        private Suggestion suggestion;

        public Suggestion getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(Suggestion suggestion) {
            this.suggestion = suggestion;
        }

        public class Suggestion {
            private Dressing dressing;
            private Uv uv;

            public Dressing getDressing() {
                return dressing;
            }

            public void setDressing(Dressing dressing) {
                this.dressing = dressing;
            }

            public Uv getUv() {
                return uv;
            }

            public void setUv(Uv uv) {
                this.uv = uv;
            }

            public class Dressing {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }
            public class Uv {
                private String brief;
                private String details;

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getDetails() {
                    return details;
                }

                public void setDetails(String details) {
                    this.details = details;
                }
            }

        }
    }
}
