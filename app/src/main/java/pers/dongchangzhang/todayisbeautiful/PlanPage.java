package pers.dongchangzhang.todayisbeautiful;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pers.dongchangzhang.todayisbeautiful.adapter.CityAdapter;
import pers.dongchangzhang.todayisbeautiful.adapter.PlanAdapter;
import pers.dongchangzhang.todayisbeautiful.dao.MyDatabaseOperator;
import pers.dongchangzhang.todayisbeautiful.entity.PlanBean;
import pers.dongchangzhang.todayisbeautiful.inter.MyItemOnClickListener;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;
import pers.dongchangzhang.todayisbeautiful.utils.GetHttpInfo;
import pers.dongchangzhang.todayisbeautiful.utils.MyDecoration;

import static pers.dongchangzhang.todayisbeautiful.Config.DB_NAME;
import static pers.dongchangzhang.todayisbeautiful.Config.DB_VERSION;

/**
 * Created by cc on 17-7-20.
 */

public class PlanPage extends Fragment {

    private static final String TAG = "PlanPage";
    private PlanAdapter adapter;
    List<PlanBean> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_page,container,false);

       initData();



        RecyclerView plans = (RecyclerView) view.findViewById(R.id.plan_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager((MainActivity)getActivity());
        plans.setLayoutManager(layoutManager);
        adapter = new PlanAdapter((MainActivity)getActivity(), list);

        plans.addItemDecoration(new MyDecoration((MainActivity)getActivity(), MyDecoration.VERTICAL_LIST));

        adapter.setItemOnClickListener(new MyItemOnClickListener() {
            @Override
            public void onItemOnClick(View view, int position) {

            }
        });
        plans.setAdapter(adapter);

        CardView cardView = (CardView)view.findViewById(R.id.add_plan);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addPlan();
            }
        });

        return view;
    }
    private void addPlan() {
        Toast.makeText((MainActivity)getActivity(), "add", Toast.LENGTH_SHORT).show();
        ((MainActivity) getActivity()).newAPlan();
    }

    void initData() {
        list.clear();
        MyDatabaseOperator operator = new MyDatabaseOperator((MainActivity)getActivity(), DB_NAME, DB_VERSION);
        List<Map> maps= operator.search("PlanInformations");
        for (Map m : maps) {
            list.add(new PlanBean(Integer.parseInt(m.get("id").toString()), m.get("checked").toString(), m.get("title").toString(), m.get("time").toString(),m.get("content").toString()));
            Log.d(TAG, "onCreateView: " + m.get("time"));
            Log.d(TAG, "onCreateView: " + m.get("title"));
            Log.d(TAG, "onCreateView: " + m.get("content"));
        }
    }
    public void reRefresh() {
        initData();
        adapter.notifyDataSetChanged();
    }
}