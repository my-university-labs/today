package pers.dongchangzhang.todayisbeautiful;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pers.dongchangzhang.todayisbeautiful.adapter.CityAdapter;
import pers.dongchangzhang.todayisbeautiful.entity.CityEntity;
import pers.dongchangzhang.todayisbeautiful.entity.TodayWeatherEntity;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;
import pers.dongchangzhang.todayisbeautiful.utils.GetHttpInfo;

import static android.content.ContentValues.TAG;


/**
 * Created by cc on 17-7-20.
 */

public class CityPage extends Fragment  {
    private ListView listView;
    private CityAdapter adapter;
    private Context context;
    List<CityEntity> list = new ArrayList<>();

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ((MainActivity)getActivity()).jumpToWeatherPage((String)msg.obj);
                    break;
                default:
                    list.clear();
                    List<CityEntity> tmp = (List<CityEntity>) msg.obj;
                    for (int i = 0; i < tmp.size(); ++i) {
                        list.add(tmp.get(i));
                    }
                    adapter.notifyDataSetChanged();
            }
        }
    };
    public CityPage() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_page,container,false);
        listView = (ListView) view.findViewById(R.id.city_list);
        adapter = new CityAdapter(getActivity(), R.layout.city_item, list);
        listView.setAdapter(adapter);
        final String code = getArguments().getString("code");
        final String url = getArguments().getString("url");
        final String city = getArguments().getString("city");
        GetHttpInfo.getCityInfo(handler, url, code, city);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ((MainActivity)getActivity()).changeToCity(url + "/" + code, list.get(position).getId(), list.get(position).getName());
            }
        });
        return view;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
