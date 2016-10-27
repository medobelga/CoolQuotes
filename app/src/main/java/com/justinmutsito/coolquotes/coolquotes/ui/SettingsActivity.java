package com.justinmutsito.coolquotes.coolquotes.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinmutsito.coolquotes.coolquotes.R;
import com.justinmutsito.coolquotes.coolquotes.alarms.Alarm;
import com.justinmutsito.coolquotes.coolquotes.preferences.Preferences;

import at.markushi.ui.CircleButton;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {
    private Preferences mPreferences;
    private String mTheme;
    private String mNotificationTime;
    private Alarm mAlarm;


    @Bind(R.id.themeLabel)
    TextView mThemeLabel;
    @Bind(R.id.notificationsLabel)
    TextView mNotificationLabel;
    @Bind(R.id.brownCheckbox)
    CircleButton mBrown;
    @Bind(R.id.blueCheckbox)
    CircleButton mBlue;

    @Bind(R.id.aboutButton)
    Button mAbout;
    @Bind(R.id.backgroundImage)
    ImageView mBackgroundImage;
    @Bind(R.id.fadedImage)
    ImageView mFadedImage;
    @Bind(R.id.zBrownLabel)
    TextView mThemeBrownLabel;
    @Bind(R.id.cBlueLabel)
    TextView mThemeBlueLabel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        mPreferences = new Preferences(this);
        mAlarm = new Alarm(this);

        //Get theme and notification time, set theme..
        mTheme = mPreferences.getMyTheme();
        mNotificationTime = mPreferences.getNotificationTime();
        setMyTheme(mTheme);


    }


    @OnClick(R.id.brownCheckbox)
    public void setBrown() {
        if (mTheme.equals("blue")) {
            saveTheme("brown");
            resetTheme();
        }


    }

    private void brownTheme() {
        mBlue.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
        mBrown.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
        setBrownTheme();
        setIcons(mNotificationTime);

    }

    @OnClick(R.id.blueCheckbox)
    public void setBlue() {
        if (mTheme.equals("brown")) {
            saveTheme("blue");
            resetTheme();
        }

    }

    private void blueTheme() {

        mBrown.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_white_48dp);
        mBlue.setImageResource(R.drawable.ic_checkbox_marked_circle_white_48dp);
        setBlueTheme();
        setIcons(mNotificationTime);

    }

    private void setIcons(String notificationTime) {

        if (notificationTime.equals(getString(R.string.no_notifications))) {
            notificationsOff();
        } else if (notificationTime.equals(getString(R.string.morning))) {
            notificationsOnMorning();
        } else {
            notificationsOnEvening();
        }


    }



    private void notificationsOff() {
        mNotificationTime = "off";
        if (mTheme.equals("brown")) {


        } else {

        }
    }


    private void notificationsOnMorning() {


        if (mTheme.equals("brown")) {

        } else {


        }
    }




    private void notificationsOnEvening() {



        if (mTheme.equals("brown")) {

        } else {

        }
    }


    @OnClick(R.id.aboutButton)
    public void about() {

        startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
    }


    private void saveTheme(String theme) {
        mPreferences.saveTheme(theme);

    }

    private void saveTime(String notificationTime) {
        mPreferences.saveTime(notificationTime);
    }

    private void setBlueTheme() {
        //Do not merge setBlueTheme and setBrownTheme to one method because in v2 the themes might not have any similar attributes.
        String white = "#ffffff";
        String lightBlue = "#80B3E5FC";

        mBackgroundImage.setImageResource(R.drawable.bg_blue);
        mFadedImage.setImageResource(R.color.blueFaded);
        mThemeLabel.setTextColor(Color.parseColor(white));
        mNotificationLabel.setTextColor(Color.parseColor(white));
        mAbout.setTextColor(Color.parseColor(white));
        mAbout.setBackground(getResources().getDrawable(R.drawable.bg_blue_circle_gradient));
        mThemeBrownLabel.setTextColor(Color.parseColor(white));
        mThemeBlueLabel.setTextColor(Color.parseColor(white));



    }

    private void setBrownTheme() {
        //Do not merge setBlueTheme and setBrownTheme to one method because in v2 the themes might not have any similar attributes.
        String darkGrey = "#212121";
        String lightGrey = "#757575";
        String lightBrown = "#90D7CCC8";
        mBackgroundImage.setImageResource(R.drawable.bg_brown);
        mFadedImage.setImageResource(R.color.brownFaded);
        mThemeLabel.setTextColor(Color.parseColor(darkGrey));
        mNotificationLabel.setTextColor(Color.parseColor(darkGrey));
        mAbout.setTextColor(Color.parseColor(darkGrey));
        mAbout.setBackground(getResources().getDrawable(R.drawable.bg_brown_circle_gradient));
        mThemeBrownLabel.setTextColor(Color.parseColor(lightGrey));
        mThemeBlueLabel.setTextColor(Color.parseColor(lightGrey));



    }


    private void setMyTheme(String theme) {

        if (theme.equals("brown")) {
            brownTheme();
        } else {
            blueTheme();

        }

    }

    private void setNotificationTime(String notificationTime) {

    }

    private void resetTheme() {
        Intent intent = new Intent(SettingsActivity.this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

//Cancel old alarm time and set new.
    private void changeNotificationTime(String notificationTime) {
        saveTime(mNotificationTime);

        if (notificationTime.equals(getString(R.string.morning))) {
            //Set Morning
            mAlarm.cancel();
            mAlarm.setAlarm(9);
        } else if (notificationTime.equals(getString(R.string.evening))) {
            //Set evening
            mAlarm.cancel();
            mAlarm.setAlarm(21);
        } else {
            //cancel
            mAlarm.cancel();

        }

    }
}




