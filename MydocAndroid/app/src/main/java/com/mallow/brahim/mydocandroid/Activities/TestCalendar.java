package com.mallow.brahim.mydocandroid.Activities;

import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.mallow.brahim.mydocandroid.R;
import com.mallow.brahim.mydocandroid.Utils.MyCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by brahim on 8/22/17.
 */

public class TestCalendar extends AppCompatActivity {

    private WeekView weekView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_calendar);

        weekView = (WeekView)findViewById(R.id.weekView);

        weekView.setOnEventClickListener(new WeekView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent event, RectF eventRect) {

            }
        });

        weekView.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

                /*Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, 3);
                startTime.set(Calendar.MINUTE, 12);
                startTime.set(Calendar.MONTH, newMonth-1);
                startTime.set(Calendar.YEAR, newYear);
                Calendar endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR, 2);
                endTime.set(Calendar.MONTH, newMonth-1);*/


                //MyCalendar myCalendar = new MyCalendar(d, newYear, newMonth);
                //Calendar startTime = new GregorianCalendar(2017, 8, 29, 9, 0);
                //Calendar endTime = new GregorianCalendar(2017, 8, 29, 10, 15);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date startDate = null;
                try {
                     startDate = dateFormat.parse("2017-09-29 10:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar startTime = Calendar.getInstance();
                startTime.setTime(startDate);

                Calendar endTime =(Calendar) startTime.clone();
                endTime.add(Calendar.HOUR_OF_DAY, 1);


                //Toast.makeText(getBaseContext(), calendar.get(Calendar.YEAR) + " - "+ calendar.get(Calendar.MONTH) + "-"+calendar.get(Calendar.DAY_OF_MONTH) + "  "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE), Toast.LENGTH_LONG).show();
                WeekViewEvent event = new WeekViewEvent(1, "Hello", startTime, endTime);

                List<WeekViewEvent> events = new ArrayList<>();
                events.add(event);

                return events;


            }
        });

        weekView.setEventLongPressListener(new WeekView.EventLongPressListener() {
            @Override
            public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
                Toast.makeText(getBaseContext(), event.getName(), Toast.LENGTH_LONG).show();

            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }
}
