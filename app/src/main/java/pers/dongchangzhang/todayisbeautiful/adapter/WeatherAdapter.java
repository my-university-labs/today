package pers.dongchangzhang.todayisbeautiful.adapter;

/**
 * Created by cc on 17-7-19.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import pers.dongchangzhang.todayisbeautiful.entity.FutureWeather;
import pers.dongchangzhang.todayisbeautiful.entity.MoreWeatherInfo;
import pers.dongchangzhang.todayisbeautiful.entity.TodayWeather;
import pers.dongchangzhang.todayisbeautiful.entity.Weather;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;
import pers.dongchangzhang.todayisbeautiful.utils.Tools;

import static android.content.ContentValues.TAG;
import static pers.dongchangzhang.todayisbeautiful.Config.weather_information;
import static pers.dongchangzhang.todayisbeautiful.Config.which_city;
import static pers.dongchangzhang.todayisbeautiful.Config.which_image;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Weather> mWeatherEntityList;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public enum ITEM_TYPE {
        ITEM1,
        ITEM2,
        ITEM3,
    }


    public WeatherAdapter(Context context, List<Weather> objects) {
        this.mWeatherEntityList = objects;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new Item1ViewHolder(mLayoutInflater.inflate(R.layout.now_weather, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM2.ordinal()) {
            return new Item2ViewHolder(mLayoutInflater.inflate(R.layout.future_weather, parent, false));
        } else {
            return new Item3ViewHolder(mLayoutInflater.inflate(R.layout.more_weather_info, parent, false));

        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Item1ViewHolder) {

            TodayWeather weatherEntity = (TodayWeather) mWeatherEntityList.get(position);
            Log.d(TAG, "onBindViewHolder: " + weatherEntity.getNow().getCode());
            Glide
                    .with(context)
                    .load(Tools.getImageByReflect(context, "w" + weatherEntity.getNow().getCode()))
                    .centerCrop()
                    .placeholder(R.drawable.w99)
                    .error(R.drawable.w99)
                    .crossFade()
                    .thumbnail(0.1f).into(((Item1ViewHolder) holder).img);
            ((Item1ViewHolder) holder).temp.setText(weatherEntity.getNow().getTemperature() + "℃");
            ((Item1ViewHolder) holder).time.setText(weatherEntity.getLocation().getPath() + "   " + formatTime(weatherEntity.getLast_update()));
            ((Item1ViewHolder) holder).info.setText(formatTodayWeatherInfo(weatherEntity.getToday().getWind_direction(), weatherEntity.getNow().getText(), weatherEntity.getToday().getHigh(), weatherEntity.getToday().getLow()));
        } else if (holder instanceof Item2ViewHolder) {
            FutureWeather weatherEntity = (FutureWeather) mWeatherEntityList.get(position);
            Glide
                    .with(context)
                    .load(Tools.getImageByReflect(context, "w" + weatherEntity.getDaily().getCode_day()))
                    .centerCrop()
                    .placeholder(R.drawable.w99)
                    .error(R.drawable.w99)
                    .crossFade()
                    .thumbnail(0.1f).into(((Item2ViewHolder) holder).day);
            Glide
                    .with(context)
                    .load(Tools.getImageByReflect(context, "w" + weatherEntity.getDaily().getCode_night()))
                    .centerCrop()
                    .placeholder(R.drawable.w99)
                    .error(R.drawable.w99)
                    .crossFade()
                    .thumbnail(0.1f).into(((Item2ViewHolder) holder).night);
            ((Item2ViewHolder) holder).time.setText(weatherEntity.getDaily().getDate());
            ((Item2ViewHolder) holder).mark.setText(formatFutureMark(weatherEntity.getDaily().getText_day(),
                    weatherEntity.getDaily().getText_night(), weatherEntity.getDaily().getHigh(), weatherEntity.getDaily().getLow()));
        } else {
            MoreWeatherInfo weatherEntity = (MoreWeatherInfo) mWeatherEntityList.get(position);

            SimpleTarget target = new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, GlideAnimation<? super Drawable> glideAnimation) {
                    ((Item3ViewHolder) holder).more.setBackground(resource);
                }
            };

            Glide
                    .with(context)
                    .load(R.drawable.bg)
                    .error(R.drawable.bg)
                    .placeholder(R.drawable.bg)
                    .thumbnail(0.1f).into(target);
            ((Item3ViewHolder) holder).more.setText(formatTodayMoreInfo(weatherEntity));
        }

    }

    @Override
    public int getItemCount() {
        return mWeatherEntityList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mWeatherEntityList.size() - 1 && position > 1)
            return ITEM_TYPE.ITEM3.ordinal();
        return position == 0 ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();

    }

    public static class Item1ViewHolder extends RecyclerView.ViewHolder {
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

    public static class Item2ViewHolder extends RecyclerView.ViewHolder {
        ImageView day;
        ImageView night;
        TextView time;
        TextView mark;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            day = (ImageView) itemView.findViewById(R.id.future_status_day);
            night = (ImageView) itemView.findViewById(R.id.future_status_night);

            time = (TextView) itemView.findViewById(R.id.future_time);
            mark = (TextView) itemView.findViewById(R.id.future_mark);
        }
    }

    public static class Item3ViewHolder extends RecyclerView.ViewHolder {
        TextView more;

        public Item3ViewHolder(View itemView) {
            super(itemView);
            more = (TextView) itemView.findViewById(R.id.more);
        }
    }

    private String formatTodayWeatherInfo(String wind, String text, String high, String low) {
        return wind + "风  天气" + text + "  " + high + "℃/" + low + "℃";
    }

    private String formatTime(String time) {
        Log.d(TAG, "formatTime: " + time);
        int end = time.lastIndexOf('+');
        int start = time.indexOf('T');
        time = time.substring(start + 1, end);
        return time.substring(0, time.lastIndexOf(':'));
    }

    private String formatFutureMark(String day, String night, String high, String low) {
        return day + " / " + night + "\n" + high
                + "℃ / " + low + "℃";
    }

    private String formatTodayMoreInfo(MoreWeatherInfo weatherEntity) {
        String feels = "体感温度：" + weatherEntity.getNow().getFeels_like() + "\n";
        String humidity = "相对湿度：" + weatherEntity.getNow().getHumidity() + "\n";
        String precip = "降水概率：" + weatherEntity.getToday().getPrecip() + "\n";
        String wind_speed = "风速：" + weatherEntity.getToday().getWind_speed() + "\n";
        String high = "最高气温：" + weatherEntity.getToday().getHigh() + "\n";
        String low = "最低气温：" + weatherEntity.getToday().getLow() + "\n";
        String uv = "紫外线强度：" + weatherEntity.getUv().getBrief() + "\n\n外出建议：" + weatherEntity.getUv().getDetails() + "\n";
        String dressings = "穿衣指数：" + weatherEntity.getDressing().getBrief() + "\n\n穿衣建议：" + weatherEntity.getDressing().getDetails() + "\n";
        return wind_speed + "\n" + high + "\n" + low + "\n" + precip + "\n" + feels + "\n" + humidity + "\n" + uv + "\n" + dressings;
    }
}
