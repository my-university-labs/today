package pers.dongchangzhang.todayisbeautiful.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import pers.dongchangzhang.todayisbeautiful.entity.City;
import pers.dongchangzhang.todayisbeautiful.inter.MyItemOnClickListener;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

/**
 * Created by cc on 17-7-19.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<City> mCityEntityList;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private MyItemOnClickListener mMyItemOnClickListener;


    public CityAdapter(Context context, List<City> objects) {
        this.mCityEntityList = objects;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new ViewHolder(mLayoutInflater.inflate(R.layout.city_item, parent, false), mMyItemOnClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        City cityEntity = (City) mCityEntityList.get(position);
        holder.city.setText(cityEntity.getName());
    }

    @Override
    public int getItemCount() {
        return mCityEntityList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView city;
        View cityView;
        MyItemOnClickListener mListener;

        public ViewHolder(View itemView, MyItemOnClickListener myItemOnClickListener) {
            super(itemView);
            cityView = itemView;
            city = (TextView) itemView.findViewById(R.id.which_city);
            this.mListener = myItemOnClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemOnClick(view, getPosition());
            }
        }
    }

    public void setItemOnClickListener(MyItemOnClickListener listener) {
        mMyItemOnClickListener = listener;
    }

}
