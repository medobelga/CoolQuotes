package com.justinmutsito.coolquotes.coolquotes.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinmutsito.coolquotes.coolquotes.R;
import com.justinmutsito.coolquotes.coolquotes.alarms.Alarm;
import com.justinmutsito.coolquotes.coolquotes.preferences.Preferences;
import com.rey.material.app.TimePickerDialog;

import at.markushi.ui.CircleButton;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {
    private Preferences mPreferences;
    private String mTheme;
    private int mNotificationTime;


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

    @Bind(R.id.notificationsOffButton)
    Button mNotificationOff;
    @Bind(R.id.notificationsOnButton)
    Button mNotificationOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        mPreferences = new Preferences(this);


        //Get theme and notification time, set theme..
        mTheme = mPreferences.getMyTheme();
        mNotificationTime = mPreferences.getNotificationTime();
        setMyTheme(mTheme);


    }


    @OnClick(R.id.brownCheckbox)
    public void setBrown() {
        if (mTheme.equals("blue")) {
            mPreferences.setTheme("brown");
            resetTheme();
        }


    }

    private void brownTheme() {
        mBlue.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
        mBrown.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
        setBrownTheme();

    }

    @OnClick(R.id.blueCheckbox)
    public void setBlue() {
        if (mTheme.equals("brown")) {
            mPreferences.setTheme("blue");
            resetTheme();
        }

    }

    private void blueTheme() {

        mBrown.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_white_48dp);
        mBlue.setImageResource(R.drawable.ic_checkbox_marked_circle_white_48dp);
        setBlueTheme();


    }



    @OnClick(R.id.notificationsOffButton)
    public void setNotificationOff() {
        mPreferences.setNotificationTime(0);
        mNotificationOff.setBackground(getResources().getDrawable(R.drawable.bg_notification_choice_gradient));
        mNotificationOn.setBackground(getResources().getDrawable(R.drawable.bg_brown_circle_gradient));

    }

    @OnClick(R.id.notificationsOnButton)
    public void setNotificationOn() {

        final TimePickerDialog dialog = new TimePickerDialog(SettingsActivity.this);
        dialog.positiveAction(getString(R.string.set));
        dialog.positiveActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = dialog.getHour();
                int minute = dialog.getMinute();
                int time = (hour * 3600) + (minute * 60);
                mPreferences.setNotificationTime(time);
                Alarm alarm= new Alarm(SettingsActivity.this);
                alarm.setAlarm(hour,minute);
                dialog.dismiss();
            }
        });
        dialog.show();
        mNotificationOn.setBackground(getResources().getDrawable(R.drawable.bg_notification_choice_gradient));
        mNotificationOff.setBackground(getResources().getDrawable(R.drawable.bg_brown_circle_gradient));


    }


    @OnClick(R.id.aboutButton)
    public void about() {

        startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
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


    private void resetTheme() {
        Intent intent = new Intent(SettingsActivity.this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}




