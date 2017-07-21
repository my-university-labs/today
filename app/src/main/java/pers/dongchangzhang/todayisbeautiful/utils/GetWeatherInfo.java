package pers.dongchangzhang.todayisbeautiful.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pers.dongchangzhang.todayisbeautiful.entity.TodayWeatherEntity;
import pers.dongchangzhang.todayisbeautiful.entity.TodayWeatherFromJsonEntity;

/**
 * Created by cc on 17-7-20.
 */


import static android.content.ContentValues.TAG;
import static pers.dongchangzhang.todayisbeautiful.Config.*;

public class GetWeatherInfo {

    public GetWeatherInfo(final Handler handler, final String city, final int code) {
        new Thread() {
            @Override
            public void run() {
                String jsonData = null;
                try {
                    jsonData = get(String.format(WEATHER_NOW_INTERFACE, PRIVATE_KEY, city));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "run: " + jsonData);
                Gson gson = new Gson();
                TodayWeatherFromJsonEntity twjson = gson.fromJson(jsonData, TodayWeatherFromJsonEntity.class);
                Log.d(TAG, "run: " + twjson.getResults().get(0).getLast_update());

                TodayWeatherEntity tw = new TodayWeatherEntity();
                tw.setName(twjson.getResults().get(0).getLocation().getName());
                tw.setCountry(twjson.getResults().get(0).getLocation().getCountry());
                tw.setPath(twjson.getResults().get(0).getLocation().getPath());
                tw.setText(twjson.getResults().get(0).getNow().getText());
                tw.setCode(twjson.getResults().get(0).getNow().getCode());
                tw.setTemperature(twjson.getResults().get(0).getNow().getTemperature());
                tw.setLast_update(twjson.getResults().get(0).getLast_update());
                Message msg = new Message();
                msg.what = code;
                msg.obj = tw;
                handler.sendMessage(msg);
                try {
                    jsonData = get(String.format(WEATHER_MORE_DAYS_INTERFACE, PRIVATE_KEY, city, 0, 8));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "run: " + jsonData);

            }
        }.start();

    }

    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build();

    public String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
