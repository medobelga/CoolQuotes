package com.justinmutsito.coolquotes.coolquotes.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.justinmutsito.coolquotes.coolquotes.R;

/**
 * Created by justin on 7/29/16.
 */
public class Preferences {


    private static final String THEME = "theme";
    private static final String NOTIFICATION_TIME = "time";
    private SharedPreferences mSavedTheme;
    private SharedPreferences mSavedTime;
    private Context mContext;

    public Preferences(Context context) {
        mContext = context;
    }

    public String getMyTheme() {
        String defaultTheme = "brown";
        mSavedTheme = mContext.getSharedPreferences(THEME, 0);
        return mSavedTheme.getString(mContext.getString(R.string.themeKey), defaultTheme);
    }

    public int getNotificationTime() {
       int defaultNotificationTime = 0;
        mSavedTime = mContext.getSharedPreferences(NOTIFICATION_TIME, 0);
        return mSavedTime.getInt(mContext.getString(R.string.timeKey), defaultNotificationTime);
    }


    public void setTheme(String theme) {
        mSavedTheme = mContext.getSharedPreferences(THEME, 0);
        SharedPreferences.Editor themeEditor = mSavedTheme.edit();
        themeEditor.putString(mContext.getString(R.string.themeKey), theme);
        themeEditor.commit();
    }

    public void setNotificationTime(int time) {
        mSavedTime = mContext.getSharedPreferences(NOTIFICATION_TIME, 0);
        SharedPreferences.Editor timeEditor = mSavedTime.edit();
        timeEditor.putInt(mContext.getString(R.string.timeKey), time);
        timeEditor.commit();
    }
}

