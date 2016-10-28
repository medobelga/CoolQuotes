package com.justinmutsito.coolquotes.coolquotes.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private Alarm mAlarm;
    private int mNotificationTime;


    @Bind(R.id.themeLabel)
    TextView mThemeLabel;
    @Bind(R.id.notificationsLabel)
    TextView mNotificationLabel;
    @Bind(R.id.brownCheckbox)
    CircleButton mBrown;
    @Bind(R.id.blueCheckbox)
    CircleButton mBlue;
    @Bind(R.id.offCheckbox)
    CircleButton mOff;
    @Bind(R.id.onCheckbox)
    CircleButton mOn;

    @Bind(R.id.offLabel)
    TextView mOffLabel;
    @Bind(R.id.onLabel)
    TextView mOnLabel;

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
    @Bind(R.id.layout1)
    LinearLayout mLayout1;
    @Bind(R.id.layout2)
    LinearLayout mLayout2;
    @Bind(R.id.layout3)
    LinearLayout mLayout3;
    @Bind(R.id.layout4)
    LinearLayout mLayout4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        mPreferences = new Preferences(this);


        //Get theme and notification time, set theme..
        mTheme = mPreferences.getMyTheme();
        mNotificationTime = mPreferences.getNotificationTime();
        mAlarm = new Alarm(SettingsActivity.this);
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
        setIcons(mNotificationTime);

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
        setIcons(mNotificationTime);


    }


    @OnClick(R.id.offCheckbox)
    public void setNotificationOff() {
        mAlarm.cancel();
        mPreferences.setNotificationTime(0);

        if (mTheme.equals("brown")) {
            mOn.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
            mOff.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);

        } else {

            mOn.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_white_48dp);
            mOff.setImageResource(R.drawable.ic_checkbox_marked_circle_white_48dp);
        }

    }

    @OnClick(R.id.onCheckbox)
    public void setNotificationOn() {

        if (mTheme.equals("brown")) {
            mOff.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
            mOn.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);

        } else {

            mOff.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_white_48dp);
            mOn.setImageResource(R.drawable.ic_checkbox_marked_circle_white_48dp);
        }

        final TimePickerDialog dialog = new TimePickerDialog(SettingsActivity.this);
        dialog.positiveAction(getString(R.string.set));
        dialog.positiveActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = dialog.getHour();
                int minute = dialog.getMinute();
                int time = (hour * 3600) + (minute * 60);
                mPreferences.setNotificationTime(time);
                mAlarm.cancel();
                mAlarm.setAlarm(hour, minute);
                dialog.dismiss();
            }
        });
        dialog.show();


    }


    @OnClick(R.id.aboutButton)
    public void about() {

        startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
    }

    private void setIcons(int notificationTime) {

        if (notificationTime != 0) {
            notificationsOn();
        } else {
            notificationsOff();
        }


    }

    private void notificationsOff() {
        if (mTheme.equals("brown")) {
            mOn.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
            mOff.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);

        } else {

            mOn.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_white_48dp);
            mOff.setImageResource(R.drawable.ic_checkbox_marked_circle_white_48dp);
        }


    }

    private void notificationsOn() {
        if (mTheme.equals("brown")) {
            mOff.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
            mOn.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);

        } else {

            mOff.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_white_48dp);
            mOn.setImageResource(R.drawable.ic_checkbox_marked_circle_white_48dp);
        }

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
        mOffLabel.setTextColor(Color.parseColor(white));
        mOnLabel.setTextColor(Color.parseColor(white));
        mLayout1.setBackgroundColor(Color.parseColor(lightBlue));
        mLayout2.setBackgroundColor(Color.parseColor(lightBlue));
        mLayout3.setBackgroundColor(Color.parseColor(lightBlue));
        mLayout4.setBackgroundColor(Color.parseColor(lightBlue));


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
        mOffLabel.setTextColor(Color.parseColor(lightGrey));
        mOnLabel.setTextColor(Color.parseColor(lightGrey));
        mLayout1.setBackgroundColor(Color.parseColor(lightBrown));
        mLayout2.setBackgroundColor(Color.parseColor(lightBrown));
        mLayout3.setBackgroundColor(Color.parseColor(lightBrown));
        mLayout4.setBackgroundColor(Color.parseColor(lightBrown));


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




