package com.justinmutsito.coolquotes.coolquotes.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.justinmutsito.coolquotes.coolquotes.receivers.AlarmReceiver;

import java.util.Calendar;

/**
 * Created by justin on 8/18/16.
 */
public class Alarm {

    private Context mContext;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    public Alarm(Context context) {
        mContext = context;
        alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mContext, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
    }

    public void setAlarm(int time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, time);


        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);

    }


    public void cancel() {
        alarmManager.cancel(alarmIntent);
    }

}
