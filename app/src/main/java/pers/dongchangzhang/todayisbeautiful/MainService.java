package pers.dongchangzhang.todayisbeautiful;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import pers.dongchangzhang.todayisbeautiful.dao.MyDatabaseOperator;
import pers.dongchangzhang.todayisbeautiful.entity.Event;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

import static pers.dongchangzhang.todayisbeautiful.Config.DB_NAME;
import static pers.dongchangzhang.todayisbeautiful.Config.DB_VERSION;
import static pers.dongchangzhang.todayisbeautiful.utils.Tools.changeStringToCalendar;


public class MainService extends Service {
    public MainService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent in = new Intent("pers.dongchangzhang.broadcasereceiver.MYRECEIVER");
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, in, 0);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000*1000, pi);


        // alarm
        MyDatabaseOperator operator = new MyDatabaseOperator(this, DB_NAME, DB_VERSION);
        List<Map> maps = operator.search("Events");
        for (Map m : maps) {
            Intent in1 = new Intent("pers.dongchangzhang.broadcasereceiver.MYRECEIVER");

//            m.get("title").toString()
//            m.get("description").toString(),
//                    m.get("location").toString()
            PendingIntent pi1 = PendingIntent.getBroadcast(this, 0, in1, 0);

//            ,
            try {
                manager.set(AlarmManager.RTC_WAKEUP, changeStringToCalendar(m.get("startTime").toString()).getTimeInMillis(), pi1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

}
