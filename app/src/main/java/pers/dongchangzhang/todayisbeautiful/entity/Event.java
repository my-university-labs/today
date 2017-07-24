package pers.dongchangzhang.todayisbeautiful.entity;

import java.util.Calendar;

/**
 * Created by cc on 17-7-24.
 */

public class Event {
    private String mTitle;
    private String mDescription;
    private String mLocation;
    private int mColor;
    private Calendar mStartTime;
    private Calendar mEndTime;
    private boolean mAllDay;

    public Event(String title, String description, String location, int color, Calendar startTime, Calendar endTime, boolean allDay) {
        this.mTitle = title;
        this.mDescription = description;
        this.mLocation = location;
        this.mColor = color;
        this.mStartTime = startTime;
        this.mEndTime = endTime;
        this.mAllDay = allDay;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public Calendar getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(Calendar mStartTime) {
        this.mStartTime = mStartTime;
    }

    public Calendar getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(Calendar mEndTime) {
        this.mEndTime = mEndTime;
    }

    public boolean ismAllDay() {
        return mAllDay;
    }

    public void setmAllDay(boolean mAllDay) {
        this.mAllDay = mAllDay;
    }
}
