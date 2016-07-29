package com.justinmutsito.coolquotes.coolquotes.Settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.justinmutsito.coolquotes.coolquotes.R;

/**
 * Created by justin on 7/29/16.
 */
public class Preferences {


    private static final String THEME = "theme";
    private SharedPreferences mSavedTheme;
    private SharedPreferences mSavedTime;
    private static final String TIME = "time";
    private String mTheme;
    private int mTime;
    private String mDefault = "#673AB7";
    private Context mContext;

    public Preferences(Context context) {
        mContext = context;
    }

    public String getMyTheme() {
        mSavedTheme = mContext.getSharedPreferences(THEME, 0);
        mTheme = mSavedTheme.getString(mContext.getString(R.string.themeKey), mDefault);
        return mTheme;
    }

    public int getNotificationTime(){
        mSavedTime = mContext.getSharedPreferences(TIME, 0);
        mTime = mSavedTime.getInt(mContext.getString(R.string.timeKey), 0);
        return mTime;
    }


    public void saveTheme(String theme) {
        mSavedTheme = mContext.getSharedPreferences(THEME, 0);
        SharedPreferences.Editor editorTheme = mSavedTheme.edit();
        editorTheme.putString(mContext.getString(R.string.themeKey), theme);
        editorTheme.commit();
    }

    private void saveTime(int time) {
        mSavedTime = mContext.getSharedPreferences(TIME, 0);
        SharedPreferences.Editor editorTime = mSavedTime.edit();
        editorTime.putInt(mContext.getString(R.string.timeKey), time);
        editorTime.commit();
    }
}

