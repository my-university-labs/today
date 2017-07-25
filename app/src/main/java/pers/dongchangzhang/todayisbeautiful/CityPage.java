package pers.dongchangzhang.todayisbeautiful;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pers.dongchangzhang.todayisbeautiful.adapter.CityAdapter;
import pers.dongchangzhang.todayisbeautiful.entity.City;
import pers.dongchangzhang.todayisbeautiful.inter.MyItemOnClickListener;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;
import pers.dongchangzhang.todayisbeautiful.utils.MyDecoration;
import pers.dongchangzhang.todayisbeautiful.utils.Tools;

import static android.content.ContentValues.TAG;
import static pers.dongchangzhang.todayisbeautiful.Config.province;


/**
 * Created by cc on 17-7-20.
 */

public class CityPage extends Fragment {
    private CityAdapter adapter;
    List<City> list = new ArrayList<>();

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ((MainActivity) getActivity()).jumpToWeatherPage(province + (String) msg.obj);
                    break;
                default:
                    list.clear();

                    List<City> tmp = (List<City>) msg.obj;
                    Log.d(TAG, "handleMessage: " + tmp.size());
                    for (int i = 0; i < tmp.size(); ++i) {
                        list.add(tmp.get(i));
                    }
                    adapter.notifyDataSetChanged();
            }
        }
    };

    public CityPage() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_page, container, false);


        RecyclerView citys = (RecyclerView) view.findViewById(R.id.city_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager((MainActivity) getActivity());
        citys.setLayoutManager(layoutManager);
        final String id = getArguments().getString("id");
        final String parent_id = getArguments().getString("parent_id");
        final String city = getArguments().getString("city");
        list = Tools.getCityInfoFromDB(id, parent_id);
        if (list.size() == 0) {
            Message msg = new Message();
            msg.what = 0;
            msg.obj = city;
            handler.sendMessage(msg);
        }
        adapter = new CityAdapter((MainActivity) getActivity(), list);

        citys.addItemDecoration(new MyDecoration((MainActivity) getActivity(), MyDecoration.VERTICAL_LIST));


        adapter.setItemOnClickListener(new MyItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int position) {
                if (list.get(position).getParent_id().equals("0")) {
                    province = list.get(position).getName();
                }
                ((MainActivity) getActivity()).changeToCity(list.get(position).getId(), list.get(position).getParent_id(), list.get(position).getName());

            }

            @Override
            public boolean onItemOnLongClick(View view, int postion) {
                return false;
            }
        });
        citys.setAdapter(adapter);

        return view;
    }

}
