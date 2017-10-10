package com.mallow.brahim.mydocandroid.Utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by brahim on 9/27/17.
 */

public  class MyCalendar  {

    private Calendar startTime, endTime;

    public MyCalendar(Date date, int newYear, int newMonth){
        startTime = Calendar.getInstance();

        startTime.set(Calendar.HOUR_OF_DAY, date.getHours());
        startTime.set(Calendar.MINUTE, date.getMinutes());
        startTime.set(Calendar.MONTH, date.getMonth() );
        startTime.set(Calendar.YEAR, newYear);

        endTime = (Calendar)startTime.clone();
        endTime.add(Calendar.MINUTE, 15);

    }


    public Calendar getStartTime() {
        return startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }
}
