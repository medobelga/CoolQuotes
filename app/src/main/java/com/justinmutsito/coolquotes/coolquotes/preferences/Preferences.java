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
    private static final String APP_LAUNCH = "launch";
    private SharedPreferences mSavedTheme;
    private SharedPreferences mSavedTime;
    private SharedPreferences mLaunchTime;
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


    public int getLauchTime() {
        int defaultLaunchTime = 1;
        mLaunchTime = mContext.getSharedPreferences(APP_LAUNCH, 0);
        return mLaunchTime.getInt(mContext.getString(R.string.launch), defaultLaunchTime);
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

    public void setLaunchTime(int time) {
        mLaunchTime = mContext.getSharedPreferences(APP_LAUNCH, 0);
        SharedPreferences.Editor timeEditor = mLaunchTime.edit();
        timeEditor.putInt(mContext.getString(R.string.launch), time);
        timeEditor.commit();
    }
}

