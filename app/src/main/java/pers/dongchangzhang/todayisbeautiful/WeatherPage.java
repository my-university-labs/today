package pers.dongchangzhang.todayisbeautiful;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pers.dongchangzhang.todayisbeautiful.adapter.WeatherAdapter;
import pers.dongchangzhang.todayisbeautiful.entity.TodayWeatherEntity;
import pers.dongchangzhang.todayisbeautiful.entity.WeatherEntity;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;
import pers.dongchangzhang.todayisbeautiful.utils.GetWeatherInfo;
import pers.dongchangzhang.todayisbeautiful.utils.Tools;

import static pers.dongchangzhang.todayisbeautiful.Config.START_REFRESH;

/**
 * Created by cc on 17-7-20.
 */

public class WeatherPage extends Fragment {

    private int city_code;
    private Context context;
    private WeatherAdapter adapter;
    private List<WeatherEntity> list = new ArrayList<>();

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Log.d("TAG", "handleMessage: fsdaidjfas");
            switch(msg.what) {
                case START_REFRESH:
                    TodayWeatherEntity tw = (TodayWeatherEntity) msg.obj;

                    list.add(new WeatherEntity(context, tw));
//                    list.add(new WeatherEntity(Tools.getImageByReflect(context, "w" + tw.getCode()), tw.getLast_update(), tw.getText(), tw.getTemperature(), tw.getTemperature()));
                    for (int i = 0; i < 5; ++i)
                        list.add(new WeatherEntity(Tools.getImageByReflect(context, "w"+i), "", "", "", ""));
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    public WeatherPage() {}

    public int getCity_code() {
        return city_code;
    }

    public void setCity_code(int city_code) {
        this.city_code = city_code;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.weather_page,container,false);
        // future
        new GetWeatherInfo(handler, "哈尔滨", START_REFRESH);

        RecyclerView future = (RecyclerView) view.findViewById(R.id.weather);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        future.setLayoutManager(layoutManager);
        adapter = new WeatherAdapter(context, list);
        future.setAdapter(adapter);
        return view;
    }
}
