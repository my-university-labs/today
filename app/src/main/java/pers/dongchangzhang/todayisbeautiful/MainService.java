package pers.dongchangzhang.todayisbeautiful;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pers.dongchangzhang.todayisbeautiful.dao.MyDatabaseOperator;
import pers.dongchangzhang.todayisbeautiful.entity.Event;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

import static pers.dongchangzhang.todayisbeautiful.Config.DB_NAME;
import static pers.dongchangzhang.todayisbeautiful.Config.DB_VERSION;
import static pers.dongchangzhang.todayisbeautiful.utils.Tools.changeStringToCalendar;


public class MainService extends Service {
    private static int code = 1;
    List<PendingIntent> bags = new ArrayList<>();
    private  AlarmBinder mAlarmBinder = new AlarmBinder();

    class AlarmBinder extends Binder {
        public void updateAlarm() {
            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Log.d("Alarm", "addAlarm: add");
            for (PendingIntent pi : bags) {
                manager.cancel(pi);
            }
            code = 1;
            addAlarm(manager);
        }
    }
    public MainService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mAlarmBinder;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent in = new Intent("pers.dongchangzhang.broadcasereceiver.MYRECEIVER");
        in.putExtra("type", "not_alarm");
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, in, 0);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),20*60*1000, pi);
        addAlarm(manager);
        return super.onStartCommand(intent, flags, startId);
    }
    private void addAlarm(AlarmManager manager) {
        MyDatabaseOperator operator = new MyDatabaseOperator(this, DB_NAME, DB_VERSION);
        List<Map> maps = operator.search("Events");
        for (Map m : maps) {
            try {
                if ( changeStringToCalendar(m.get("startTime").toString()).getTimeInMillis() > System.currentTimeMillis()) {
                    Intent in1 = new Intent("pers.dongchangzhang.broadcasereceiver.MYRECEIVER");
                    in1.putExtra("type", "alarm");
                    in1.putExtra("title", m.get("title").toString());
                    in1.putExtra("description", m.get("description").toString());
                    PendingIntent pi1 = PendingIntent.getBroadcast(this, code++, in1, 0);
                    bags.add(pi1);
                    try {
                        manager.set(AlarmManager.RTC_WAKEUP, changeStringToCalendar(m.get("startTime").toString()).getTimeInMillis(), pi1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
