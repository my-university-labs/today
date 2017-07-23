package pers.dongchangzhang.todayisbeautiful.entity;

/**
 * Created by cc on 17-7-23.
 */

public class PlanBean {
    private int id;
    private String checked;
    private String plan_title;
    private String plan_time;
    private String plan_content;
    public PlanBean() {}
    public PlanBean(int id, String checked,  String plan_title, String plan_time, String plan_content) {
        this.id = id;
        this.checked = checked;
        this.plan_title = plan_title;
        this.plan_time = plan_time;
        this.plan_content = plan_content;
    }

    public String getPlan_title() {
        return plan_title;
    }

    public void setPlan_title(String plan_title) {
        this.plan_title = plan_title;
    }

    public String getPlan_time() {
        return plan_time;
    }

    public void setPlan_time(String plan_time) {
        this.plan_time = plan_time;
    }

    public String getPlan_content() {
        return plan_content;
    }

    public void setPlan_content(String plan_content) {
        this.plan_content = plan_content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
