package pers.dongchangzhang.todayisbeautiful;

/**
 * Created by cc on 17-7-19.
 */

public class Config {
    public static final String WEATHER_NOW_INTERFACE = "https://api.seniverse.com/v3/weather/now.json?key=%s&location=%s&language=zh-Hans&unit=c";
    public static final String WEATHER_MORE_DAYS_INTERFACE = "https://api.seniverse.com/v3/weather/daily.json?key=%s&location=%s&language=zh-Hans&unit=c&start=%s&days=%s";
    public static final String USER_ID = "U290A5A354";
    public static final String PRIVATE_KEY = "gl3b1rm9qlekbucw";

    public final static int CONNECT_TIMEOUT = 60;
    public final static int READ_TIMEOUT = 100;
    public final static int WRITE_TIMEOUT = 60;


    public final static int START_REFRESH = 1;
}
