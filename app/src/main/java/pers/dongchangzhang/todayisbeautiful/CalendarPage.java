package pers.dongchangzhang.todayisbeautiful;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import pers.dongchangzhang.todayisbeautiful.dao.MyDatabaseOperator;
import pers.dongchangzhang.todayisbeautiful.entity.Event;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static pers.dongchangzhang.todayisbeautiful.Config.DB_NAME;
import static pers.dongchangzhang.todayisbeautiful.Config.DB_VERSION;
import static pers.dongchangzhang.todayisbeautiful.Config.color_times;
import static pers.dongchangzhang.todayisbeautiful.Config.colors;
import static pers.dongchangzhang.todayisbeautiful.utils.Tools.changeStringToCalendar;


/**
 * Created by cc on 17-7-23.
 */

public class CalendarPage extends Fragment implements CalendarPickerController {
    AgendaCalendarView mAgendaCalendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_page, container, false);
        mAgendaCalendarView = (AgendaCalendarView) view.findViewById(R.id.agenda_calendar_view);

        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -10);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);

        List<CalendarEvent> eventList = new ArrayList<>();

        try {
            mockList(eventList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
        ;
        return view;
    }

    private void mockList(List<CalendarEvent> eventList) throws ParseException {
        BaseCalendarEvent event;
        MyDatabaseOperator operator = new MyDatabaseOperator((MainActivity) getActivity(), DB_NAME, DB_VERSION);
        List<Map> maps = operator.search("Events");
        Random r = new Random();
        for (Map m : maps) {
            int which = r.nextInt(color_times);
            Log.d(TAG, "mockList: " + which);
            event = new BaseCalendarEvent(
                    m.get("title").toString(),
                    m.get("description").toString(),
                    m.get("location").toString(),
                    colors[which],
                    changeStringToCalendar(m.get("startTime").toString()),
                    changeStringToCalendar(m.get("startTime").toString()),
                    false);
            Log.d(TAG, "onCreateView: " + m.get("time"));
            Log.d(TAG, "onCreateView: " + m.get("title"));
            Log.d(TAG, "onCreateView: " + m.get("content"));
            eventList.add(event);
        }


    }

    @Override
    public void onDaySelected(DayItem dayItem) {

    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        Toast.makeText(getActivity(), event.getTitle() , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onScrollToDate(Calendar calendar) {

    }
}


