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
        message.getMsg("开始更新天气信息...");
    }

    interface Message {
        public void getMsg(String str);
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}