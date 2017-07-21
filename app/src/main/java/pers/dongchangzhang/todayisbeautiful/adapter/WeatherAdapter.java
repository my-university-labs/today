package pers.dongchangzhang.todayisbeautiful.adapter;

/**
 * Created by cc on 17-7-19.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import pers.dongchangzhang.todayisbeautiful.entity.WeatherEntity;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

/**
 * 照片栏目的适配器
 * Created by me on 16-12-21.
 */
public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<WeatherEntity> mWeatherEntityList;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public enum ITEM_TYPE {
        ITEM1,
        ITEM2,
        ITEM3,
    }


    public WeatherAdapter(Context context, List<WeatherEntity> objects) {
        this.mWeatherEntityList = objects;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new Item1ViewHolder(mLayoutInflater.inflate(R.layout.now_weather, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM2.ordinal()){
            return new Item2ViewHolder(mLayoutInflater.inflate(R.layout.future_weather, parent, false));
        } else {
            return new Item3ViewHolder(mLayoutInflater.inflate(R.layout.more_weather_info, parent, false));

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Item1ViewHolder) {
//            ((Item1ViewHolder) holder).mTextView.setText(titles[position]);
            WeatherEntity weatherEntity = mWeatherEntityList.get(position);
            Glide
                    .with(context)
                    .load(weatherEntity.getStatus())
                    .centerCrop()
                    .placeholder(R.drawable.w99)
                    .error(R.drawable.w99)
                    .crossFade()
                    .thumbnail(0.1f).into(((Item1ViewHolder) holder).img);
            ((Item1ViewHolder) holder).temp.setText(weatherEntity.getTemp());
            ((Item1ViewHolder) holder).time.setText(weatherEntity.getLast_update());
            ((Item1ViewHolder) holder).info.setText(weatherEntity.getDescribe());
        } else if (holder instanceof Item2ViewHolder) {
            WeatherEntity weatherEntity = mWeatherEntityList.get(position);
            Glide
                    .with(context)
                    .load(weatherEntity.getStatus())
                    .centerCrop()
                    .placeholder(R.drawable.w99)
                    .error(R.drawable.w99)
                    .crossFade()
                    .thumbnail(0.1f).into(((Item2ViewHolder) holder).img);
            ((Item2ViewHolder) holder).days.setText("明天");
            ((Item2ViewHolder) holder).info.setText("23C/34C");
        } else {
            //
        }

    }

    @Override
    public int getItemCount() {
        return mWeatherEntityList.size();
    }
    @Override
    public int getItemViewType(int position) {
        if (position == mWeatherEntityList.size() - 1) return ITEM_TYPE.ITEM3.ordinal();
        return position == 0 ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();

    }

    public static class Item1ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView temp;
        TextView time;
        TextView info;
        public Item1ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.status);
            temp = (TextView) itemView.findViewById(R.id.temp);
            time = (TextView) itemView.findViewById(R.id.update_time);
            info = (TextView) itemView.findViewById(R.id.info);

        }
    }
    public static class Item2ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView days;
        TextView info;
        public Item2ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.future_status);
            days = (TextView)itemView.findViewById(R.id.future_day);
            info = (TextView)itemView.findViewById(R.id.future_info);

        }
    }
    public static class Item3ViewHolder extends RecyclerView.ViewHolder{

        public Item3ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
