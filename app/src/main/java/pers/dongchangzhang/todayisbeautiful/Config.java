package pers.dongchangzhang.todayisbeautiful;

/**
 * Created by cc on 17-7-19.
 */

import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

public class Config {
    public static final String IMAGE_API = "http://guolin.tech/api/bing_pic";
    public static final String CHINA_PROVINCE = "http://guolin.tech/api";
    public static final String CHINA_URBAN = "http://guolin.tech/api/china/%s";
    public static final String WEATHER_NOW_INTERFACE = "https://api.seniverse.com/v3/weather/now.json?key=%s&location=%s&language=zh-Hans&unit=c";
    public static final String WEATHER_MORE_DAYS_INTERFACE = "https://api.seniverse.com/v3/weather/daily.json?key=%s&location=%s&language=zh-Hans&unit=c&start=%s&days=%s";
    public static final String LIFE_INFO_INTERFACE = "https://api.seniverse.com/v3/life/suggestion.json?key=%s&location=%s&language=zh-Hans";
    public static final String USER_ID = "U290A5A354";
    public static final String PRIVATE_KEY = "gl3b1rm9qlekbucw";

    public static final int CONNECT_TIMEOUT = 60;
    public static final int READ_TIMEOUT = 100;
    public static final int WRITE_TIMEOUT = 60;
    public static final int ERROR = 0;
    public static final int START_REFRESH = 1;
    public static final int OPERATION_REFRESH = 2;
    public static String which_image;
    public static String which_city = "哈尔滨";
    public static String province;
    public static int DB_VERSION = 1;
    public static String DB_NAME = "plans.db";
    public static String FALSE = "n";
    public static String TRUE = "y";

    private static String CALANDER_URL = "content://com.android.calendar_page/calendars";
    private static String CALANDER_EVENT_URL = "content://com.android.calendar_page/events";
    private static String CALANDER_REMIDER_URL = "content://com.android.calendar_page/reminders";

    public final static int[] colors = {
            R.color.calendar_text_current_day,
            R.color.calendar_text_first_day_of_month,
            R.color.calendar_divider_color,
            R.color.topbar,
            R.color.tram,
            R.color.agenda_list_header_divider,
            R.color.colorAccent,
            R.color.colorPrimary};
    public final static int color_times = 8;

    public static String weather_information = null;

    public static boolean first_time_in = true;


}
