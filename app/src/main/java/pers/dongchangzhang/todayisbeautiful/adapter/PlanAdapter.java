package pers.dongchangzhang.todayisbeautiful.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.List;

import pers.dongchangzhang.todayisbeautiful.entity.Event;
import pers.dongchangzhang.todayisbeautiful.inter.MyItemOnClickListener;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

/**
 * Created by cc on 17-7-19.
 */

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private List<Event> mPlanList;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private MyItemOnClickListener mMyItemOnClickListener;


    public PlanAdapter(Context context, List<Event> objects) {
        this.mPlanList = objects;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.plan_item, parent, false), mMyItemOnClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event plan = (Event) mPlanList.get(position);
        holder.plan_title.setText(plan.getmTitle());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        holder.plan_time.setText(sdf.format(plan.getmStartTime().getTime()));
        holder.plan_content.setText(plan.getmDescription());
    }

    @Override
    public int getItemCount() {
        return mPlanList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView plan_title;
        TextView plan_time;
        TextView plan_content;
        View cityView;
        MyItemOnClickListener mListener;

        public ViewHolder(View itemView, MyItemOnClickListener myItemOnClickListener) {
            super(itemView);
            cityView = itemView;
            plan_title = (TextView) itemView.findViewById(R.id.plan_name);
            plan_time = (TextView) itemView.findViewById(R.id.plan_time);
            plan_content = (TextView) itemView.findViewById(R.id.plan_content);
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
