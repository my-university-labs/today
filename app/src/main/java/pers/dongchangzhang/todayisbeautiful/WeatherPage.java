package pers.dongchangzhang.todayisbeautiful;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pers.dongchangzhang.todayisbeautiful.adapter.WeatherAdapter;
import pers.dongchangzhang.todayisbeautiful.entity.Weather;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;
import pers.dongchangzhang.todayisbeautiful.utils.Tools;

/**
 * Created by cc on 17-7-20.
 */

public class WeatherPage extends Fragment {

    private int city_code;
    private Context context;
    private List<Weather> list = new ArrayList<>();

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
        for (int i = 0; i < 3; ++i)
            list.add(new Weather(Tools.getImageByReflect(context, "w"+i), "", "", "", ""));
        ListView future = (ListView) view.findViewById(R.id.future_weather);
        WeatherAdapter adapter = new WeatherAdapter(context, R.layout.future_weather, list);
        future.setAdapter(adapter);
        return view;
    }
}
