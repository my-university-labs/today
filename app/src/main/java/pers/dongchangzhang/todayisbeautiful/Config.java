package pers.dongchangzhang.todayisbeautiful;

/**
 * Created by cc on 17-7-19.
 */

public class Config {
    public static final String IMAGE_API = "http://guolin.tech/api/bing_pic";
    public static final String CHINA_PROVINCE = "http://guolin.tech/api";
    public static final String CHINA_URBAN = "http://guolin.tech/api/china/%s";
    public static final String WEATHER_NOW_INTERFACE = "https://api.seniverse.com/v3/weather/now.json?key=%s&location=%s&language=zh-Hans&unit=c";
    public static final String WEATHER_MORE_DAYS_INTERFACE = "https://api.seniverse.com/v3/weather/daily.json?key=%s&location=%s&language=zh-Hans&unit=c&start=%s&days=%s";
    public static final String LIFE_INFO_INTERFACE = "https://api.seniverse.com/v3/life/suggestion.json?key=%s&location=%s&language=zh-Hans";
    public static final String USER_ID = "U290A5A354";
    public static final String PRIVATE_KEY = "gl3b1rm9qlekbucw";

    public final static int CONNECT_TIMEOUT = 60;
    public final static int READ_TIMEOUT = 100;
    public final static int WRITE_TIMEOUT = 60;


    public final static int START_REFRESH = 1;
    public final static int OPERATION_REFRESH = 2;


    public static String which_image;
    public static String which_city = "哈尔滨";
    public static int DB_VERSION = 1;
    public static String DB_NAME = "plans.db";
    public static String CHECKED_FALSE = "n";
    public static String CHECKED_TRUE = "y";

    private static String CALANDER_URL = "content://com.android.calendar_page/calendars";
    private static String CALANDER_EVENT_URL = "content://com.android.calendar_page/events";
    private static String CALANDER_REMIDER_URL = "content://com.android.calendar_page/reminders";


}
