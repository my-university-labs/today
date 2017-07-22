package pers.dongchangzhang.todayisbeautiful.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pers.dongchangzhang.todayisbeautiful.entity.CityEntity;
import pers.dongchangzhang.todayisbeautiful.entity.FutureWeatherEntity;
import pers.dongchangzhang.todayisbeautiful.entity.FutureWeatherFromJsonEntity;
import pers.dongchangzhang.todayisbeautiful.entity.LifeInfoEntity;
import pers.dongchangzhang.todayisbeautiful.entity.LifeInfoFromJsonEntity;
import pers.dongchangzhang.todayisbeautiful.entity.MoreWeatherInfoEntity;
import pers.dongchangzhang.todayisbeautiful.entity.TodayWeatherEntity;
import pers.dongchangzhang.todayisbeautiful.entity.TodayWeatherFromJsonEntity;
import pers.dongchangzhang.todayisbeautiful.entity.WeatherEntity;

/**
 * Created by cc on 17-7-20.
 */


import static android.content.ContentValues.TAG;
import static pers.dongchangzhang.todayisbeautiful.Config.*;

public class GetHttpInfo {

    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build();

    public static void getWeatherInfo(final Handler handler, final String city, final int code) {
        new Thread() {
            @Override
            public void run() {
                List<WeatherEntity> infos = new ArrayList<>();
                String jsonData = null;
                try {
                    jsonData = get(String.format(WEATHER_NOW_INTERFACE, PRIVATE_KEY, city));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "today_now_info: " + jsonData);
                Gson gson = new Gson();
                TodayWeatherFromJsonEntity twjson = gson.fromJson(jsonData, TodayWeatherFromJsonEntity.class);


                try {
                    jsonData = get(String.format(WEATHER_MORE_DAYS_INTERFACE, PRIVATE_KEY, city, 0, 8));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "many_day_info: " + jsonData);
                gson = new Gson();
                FutureWeatherFromJsonEntity fwjson = gson.fromJson(jsonData, FutureWeatherFromJsonEntity.class);


                try {
                    jsonData = get(String.format(LIFE_INFO_INTERFACE, PRIVATE_KEY, city));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "life_info: " + jsonData);
                gson = new Gson();
                LifeInfoFromJsonEntity lijson = gson.fromJson(jsonData, LifeInfoFromJsonEntity.class);
                Log.d(TAG, "run: " + lijson);

                try {
                    which_image = get(IMAGE_API);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // today
                infos.add(new TodayWeatherEntity(twjson, fwjson));
                // future;
                FutureWeatherFromJsonEntity.Results.Location location = fwjson.getResults().get(0).getLocation();
                String last_update = fwjson.getResults().get(0).getLast_update();
                for (int i = 1; i < fwjson.getResults().get(0).getDaily().size(); ++i) {
                    infos.add(new FutureWeatherEntity(location, fwjson.getResults().get(0).getDaily().get(i), last_update));
                }
                // life
                infos.add(new MoreWeatherInfoEntity(new TodayWeatherEntity(twjson, fwjson), new LifeInfoEntity(lijson)));

                Message msg = new Message();
                msg.what = code;
                msg.obj = infos;
                handler.sendMessage(msg);

            }
        }.start();

    }
    public static void getCityInfo(final Handler handler, final String url, final String code, final String city) {
        new Thread() {
            @Override
            public void run() {
                String jsonData = null;
                List<CityEntity> list = new ArrayList<CityEntity>();
                try {
                    jsonData = get(url + "/" + code);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "run: " + jsonData);
                try {
                    JSONArray allPlaces = new JSONArray(jsonData);
                    for (int i = 0; i < allPlaces.length(); ++i) {
                        JSONObject jsonObject = allPlaces.getJSONObject(i);
                        list.add(new CityEntity(jsonObject.getString("id"), jsonObject.getString("name")));
                        Log.d(TAG, "id is " + jsonObject.getString("id") + "name is " + jsonObject.getString("name"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "run: " + list.size());

                Message msg = new Message();
                    msg.what = list.size();
                if (list.size() == 0)
                    msg.obj = city;
                else
                    msg.obj = list;
                handler.sendMessage(msg);
            }
        }.start();
    }

    public static String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
