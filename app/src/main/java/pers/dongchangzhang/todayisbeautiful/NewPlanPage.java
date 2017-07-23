package pers.dongchangzhang.todayisbeautiful;

import android.app.Fragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnChangeLisener;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import pers.dongchangzhang.todayisbeautiful.dao.MyDatabaseOperator;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static pers.dongchangzhang.todayisbeautiful.Config.CHECKED_FALSE;
import static pers.dongchangzhang.todayisbeautiful.Config.DB_NAME;
import static pers.dongchangzhang.todayisbeautiful.Config.DB_VERSION;

/**
 * Created by cc on 17-7-23.
 */

public class NewPlanPage extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_plan_page, container, false);
        final EditText select_time = (EditText)view.findViewById(R.id.when_happened);
        final EditText plan_title = (EditText)view.findViewById(R.id.new_plan_title);
        final EditText plan_content = (EditText)view.findViewById(R.id.new_plan_content);
        final Button cancel = (Button)view.findViewById(R.id.plan_cancel);
        final Button commit = (Button)view.findViewById(R.id.plan_commit);


        select_time.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    DatePickDialog dialog = new DatePickDialog((MainActivity) getActivity());
                    Log.d("1", "onTouch: ");

                    //设置上下年分限制
                    dialog.setYearLimt(5);
                    //设置标题
                    dialog.setTitle("选择时间");
                    //设置类型
                    dialog.setType(DateType.TYPE_YMDHM);
                    //设置消息体的显示格式，日期格式
                    dialog.setMessageFormat("yyyy-MM-dd HH:mm");
                    //设置选择回调
                    dialog.setOnChangeLisener(new OnChangeLisener() {
                        @Override
                        public void onChanged(Date date) {
                            String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(date);
                            select_time.setText(sdate);
                        }
                    });
                    //设置点击确定按钮回调
                    dialog.setOnSureLisener(new OnSureLisener() {
                        @Override
                        public void onSure(Date date) {
                            String sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(date);
                            select_time.setText(sdate);
                        }
                    });
                    dialog.show();
                }
                return false;
            }

        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).cancelNewPlan();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText((MainActivity)getActivity(), "commit", Toast.LENGTH_SHORT).show();
                MyDatabaseOperator operator = new MyDatabaseOperator((MainActivity)getActivity(), DB_NAME, DB_VERSION);
                ContentValues value = new ContentValues();
                value.put("time", select_time.getText().toString());
                value.put("title", plan_title.getText().toString());
                value.put("content", plan_content.getText().toString());
                value.put("checked", CHECKED_FALSE);
                operator.insert("PlanInformations", value);
                operator.close();

                List<Map> maps= operator.search("PlanInformations");
                for (Map m : maps) {
                    Log.d(TAG, "onCreateView: " + m.get("time"));
                    Log.d(TAG, "onCreateView: " + m.get("title"));
                    Log.d(TAG, "onCreateView: " + m.get("content"));
                    Log.d(TAG, "onClick: " + m.get("checked"));
                }
                ((MainActivity)getActivity()).commitNewPlan();


            }
        });
        return view;
    }
}
