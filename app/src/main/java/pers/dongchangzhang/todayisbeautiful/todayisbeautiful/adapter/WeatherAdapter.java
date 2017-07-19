package pers.dongchangzhang.todayisbeautiful.todayisbeautiful.adapter;

/**
 * Created by cc on 17-7-19.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.bumptech.glide.Glide;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.entity.Weather;

/**
 * 照片栏目的适配器
 * Created by me on 16-12-21.
 */
public class WeatherAdapter extends ArrayAdapter<Weather> {
    private int resourceId;

    List<Weather> mImageList;
    private Context context;

    public WeatherAdapter(Context context, int textViewResourceId, List<Weather> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        mImageList = objects;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Weather weather = getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            holder.img = (ImageView) convertView.findViewById(R.id.future_status);
            holder.days = (TextView) convertView.findViewById(R.id.future_day);
            holder.info = (TextView) convertView.findViewById(R.id.future_info);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        int url = weather.getStatus();
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.w99)
                .error(R.drawable.w99)
                .crossFade()
                .thumbnail(0.1f).into(holder.img);
        holder.days.setText("今天");
        holder.info.setText("23C/34C");
        return convertView;
    }
    private static class ViewHolder
    {
        public ImageView img;
        public TextView days;
        public TextView info;
    }
}