package pers.dongchangzhang.todayisbeautiful.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import pers.dongchangzhang.todayisbeautiful.entity.CityEntity;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

/**
 * Created by cc on 17-7-19.
 */

public class CityAdapter extends ArrayAdapter<CityEntity> {
    private int resourceId;

    private List<CityEntity> mCityList;
    private Context context;

    public CityAdapter(Context context, int textViewResourceId, List<CityEntity> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.mCityList = objects;
        this.resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final CityEntity city = getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            holder.city = (TextView) convertView.findViewById(R.id.which_city);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.city.setText(city.getName());
        return convertView;
    }
    private static class ViewHolder
    {
        public TextView city;
    }


}
