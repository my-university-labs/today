package pers.dongchangzhang.todayisbeautiful.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;


import java.util.List;

import pers.dongchangzhang.todayisbeautiful.entity.PlanBean;
import pers.dongchangzhang.todayisbeautiful.inter.MyItemOnClickListener;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

import static pers.dongchangzhang.todayisbeautiful.Config.CHECKED_FALSE;

/**
 * Created by cc on 17-7-19.
 */

public class PlanAdapter extends  RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private List<PlanBean> mPlanList;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private  MyItemOnClickListener mMyItemOnClickListener;


    public PlanAdapter(Context context, List<PlanBean> objects) {
        this.mPlanList = objects;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new ViewHolder(mLayoutInflater.inflate(R.layout.plan_item, parent, false), mMyItemOnClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlanBean plan = (PlanBean) mPlanList.get(position);
        holder.plan_title.setText(plan.getPlan_title());
        holder.plan_time.setText(plan.getPlan_time());
        holder.plan_content.setText(plan.getPlan_content());
        boolean checked = (plan.getChecked() == CHECKED_FALSE) ? false : true;
        holder.radioButton.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return mPlanList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView plan_title;
        TextView plan_time;
        TextView plan_content;
        RadioButton radioButton;
        View cityView;
        MyItemOnClickListener mListener;

        public ViewHolder(View itemView, MyItemOnClickListener myItemOnClickListener) {
            super(itemView);
            cityView = itemView;
            plan_title = (TextView) itemView.findViewById(R.id.plan_name);
            plan_time = (TextView)itemView.findViewById(R.id.plan_time);
            plan_content = (TextView)itemView.findViewById(R.id.plan_content);
            radioButton = (RadioButton)itemView.findViewById(R.id.checked);
            this.mListener = myItemOnClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener!=null){
                mListener.onItemOnClick(view,getPosition());
            }
        }
    }
    public void setItemOnClickListener(MyItemOnClickListener listener){
        mMyItemOnClickListener=listener;
    }

}
