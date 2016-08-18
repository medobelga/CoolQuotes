package com.justinmutsito.coolquotes.coolquotes.Settings;

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

    public String getNotificationTime() {
        String defaultNotificationTime = "morning";
        mSavedTime = mContext.getSharedPreferences(NOTIFICATION_TIME, 0);
        return mSavedTime.getString(mContext.getString(R.string.timeKey), defaultNotificationTime);
    }


    public void saveTheme(String theme) {
        mSavedTheme = mContext.getSharedPreferences(THEME, 0);
        SharedPreferences.Editor themeEditor = mSavedTheme.edit();
        themeEditor.putString(mContext.getString(R.string.themeKey), theme);
        themeEditor.commit();
    }

    public void saveTime(String  notificationTime) {
        mSavedTime = mContext.getSharedPreferences(NOTIFICATION_TIME, 0);
        SharedPreferences.Editor timeEditor = mSavedTime.edit();
        timeEditor.putString(mContext.getString(R.string.timeKey), notificationTime);
        timeEditor.commit();
    }
}

