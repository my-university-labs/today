package pers.dongchangzhang.todayisbeautiful;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pers.dongchangzhang.todayisbeautiful.adapter.WeatherAdapter;
import pers.dongchangzhang.todayisbeautiful.entity.FutureWeather;
import pers.dongchangzhang.todayisbeautiful.entity.MoreWeatherInfo;
import pers.dongchangzhang.todayisbeautiful.entity.TodayWeather;
import pers.dongchangzhang.todayisbeautiful.entity.Weather;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;
import pers.dongchangzhang.todayisbeautiful.utils.GetHttpInfo;
import pers.dongchangzhang.todayisbeautiful.utils.MyDecoration;

import static pers.dongchangzhang.todayisbeautiful.Config.ERROR;
import static pers.dongchangzhang.todayisbeautiful.Config.OPERATION_REFRESH;
import static pers.dongchangzhang.todayisbeautiful.Config.START_REFRESH;
import static pers.dongchangzhang.todayisbeautiful.Config.which_city;

/**
 * Created by cc on 17-7-20.
 */

public class WeatherPage extends Fragment {

    private WeatherAdapter adapter;
    private List<Weather> list = new ArrayList<>();
    private SwipeRefreshLayout swipFresh;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Log.d("TAG", "handleMessage: fsdaidjfas");
            List<Weather> tmp;
            switch(msg.what) {
                case START_REFRESH:
                    tmp = (List<Weather>) msg.obj;
                    list.add((TodayWeather) tmp.get(0));
                    for (int i = 1; i < tmp.size() - 1; ++i)
                        list.add((FutureWeather)tmp.get(i));
                    list.add((MoreWeatherInfo) tmp.get(tmp.size() - 1));
                    adapter.notifyDataSetChanged();
                    break;
                case OPERATION_REFRESH:
                    list.clear();
                    tmp = (List<Weather>) msg.obj;
                    list.add((TodayWeather) tmp.get(0));
                    for (int i = 1; i < tmp.size() - 1; ++i)
                        list.add((FutureWeather)tmp.get(i));
                    list.add((MoreWeatherInfo) tmp.get(tmp.size() - 1));
                    adapter.notifyDataSetChanged();
                    Toast.makeText((MainActivity) getActivity(), "更新完成", Toast.LENGTH_SHORT).show();
                    swipFresh.setRefreshing(false);
                    break;
                case ERROR:
                    list.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText((MainActivity)getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    public WeatherPage() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.weather_page,container,false);
        // future
        GetHttpInfo.getWeatherInfo(handler, which_city, START_REFRESH);

        RecyclerView future = (RecyclerView) view.findViewById(R.id.weather);
        LinearLayoutManager layoutManager = new LinearLayoutManager((MainActivity)getActivity());
        future.setLayoutManager(layoutManager);
        adapter = new WeatherAdapter((MainActivity)getActivity(), list);
        future.setAdapter(adapter);

        future.addItemDecoration(new MyDecoration((MainActivity)getActivity(), MyDecoration.VERTICAL_LIST));

        swipFresh = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipFresh.setColorSchemeResources(R.color.colorPrimary);
        swipFresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetHttpInfo.getWeatherInfo(handler, which_city, OPERATION_REFRESH);
            }
        });
        return view;
    }

    public void onRefresh(String city) {
        GetHttpInfo.getWeatherInfo(handler, city, OPERATION_REFRESH);
    }

}
