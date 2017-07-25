package pers.dongchangzhang.todayisbeautiful; /**
 * Created by cc on 17-7-25.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
    private Message message;

    @Override
    public void onReceive(Context context, Intent intent) {
        String r = intent.getStringExtra("type");
//        Toast.makeText(context, r, Toast.LENGTH_SHORT).show();
        if (r.equals("alarm")) {
            String title = intent.getStringExtra("title");
            String des = intent.getStringExtra("description");
            message.getMsg(true, title,  des);
        } else {
            message.getMsg(false, "开始更新天气信息...", null);
        }
    }

    interface Message {
        public void getMsg(boolean isAlarm, String title, String info);
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}