package com.justinmutsito.coolquotes.coolquotes.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinmutsito.coolquotes.coolquotes.R;

import at.markushi.ui.CircleButton;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {
    private static final String THEME = "theme";
    private static final String TIME = "time";
    private SharedPreferences savedTheme;
    private SharedPreferences savedTime;
    public String mTheme;
    public int mTime;

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
    @Bind(R.id.morningCheckbox)
    CircleButton mMorning;
    @Bind(R.id.eveningCheckbox)
    CircleButton mEvening;
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
    @Bind(R.id.offLabel)
    TextView mOffLabel;
    @Bind(R.id.morningLabel)
    TextView mMorningLabel;
    @Bind(R.id.eveningLabel)
    TextView mEveningLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        savedTheme = getSharedPreferences(THEME, 0);
        savedTime = getSharedPreferences(TIME, 0);
        mTheme = savedTheme.getString("ThemeKey", "brown");
        mTime = savedTime.getInt("TimeKey", 0);

        if (mTheme.equals("brown")) {
            setBrown();
        } else {
            setBlue();


        }

        if (mTime == 0) {
            setOff();
        } else if (mTime == 9) {
            setMorning();
        } else {
            setEvening();
        }

        saveTheme(mTheme);
        saveTheme(mTime);
        setSettings(mTheme, mTime);
    }


    @OnClick(R.id.brownCheckbox)
    public void setBrown() {
        mTheme = "brown";
        mBlue.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
        mBrown.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
        setBrownTheme();
        saveTheme(mTheme);


    }

    @OnClick(R.id.blueCheckbox)
    public void setBlue() {
        mTheme = "blue";
        mBrown.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
        mBlue.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
        setBlueTheme();
        saveTheme(mTheme);
    }


    @OnClick(R.id.offCheckbox)
    public void setOff() {
        mTime = 0;
        mOff.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
        mEvening.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
        mMorning.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
        saveTheme(mTime);
    }

    @OnClick(R.id.morningCheckbox)
    public void setMorning() {
        mTime = 9;
        mEvening.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
        mMorning.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
        mOff.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
        saveTheme(mTime);
    }


    @OnClick(R.id.eveningCheckbox)
    public void setEvening() {
        mTime = 21;
        mMorning.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
        mEvening.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
        mOff.setImageResource(R.drawable.ic_checkbox_blank_circle_outline_grey600_48dp);
        saveTheme(mTime);
    }


    @OnClick(R.id.aboutButton)
    public void about() {
        startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
    }

    private void setSettings(String theme, int time) {
//

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    private void saveTheme(String theme) {
        savedTheme = getSharedPreferences(THEME, 0);
        SharedPreferences.Editor editorTheme = savedTheme.edit();
        editorTheme.putString("ThemeKey", theme);
        editorTheme.commit();
    }

    private void saveTheme(int time) {
        savedTime = getSharedPreferences(TIME, 0);
        SharedPreferences.Editor editorTime = savedTime.edit();
        editorTime.putInt("TimeKey", time);
        editorTime.commit();
    }

    private void setBlueTheme() {
        String white = "#ffffff";
        mBackgroundImage.setImageResource(R.drawable.blue_bg);
        //mFadedImage.setImageResource(R.color.bluecolorPrimaryLight);
        mFadedImage.setImageResource(R.color.blueFaded);
        mThemeLabel.setTextColor(Color.parseColor(white));
        mNotificationLabel.setTextColor(Color.parseColor(white));
        mAbout.setTextColor(Color.parseColor(white));
        mThemeBrownLabel.setTextColor(Color.parseColor(white));
        mThemeBlueLabel.setTextColor(Color.parseColor(white));
        mOffLabel.setTextColor(Color.parseColor(white));
        mMorningLabel.setTextColor(Color.parseColor(white));
        mEveningLabel.setTextColor(Color.parseColor(white));


    }

    private void setBrownTheme() {
        String darkGrey = "#212121";
        mBackgroundImage.setImageResource(R.drawable.brown_bg);
        mFadedImage.setImageResource(R.color.colorFaded);
        mThemeLabel.setTextColor(Color.parseColor(darkGrey));
        mNotificationLabel.setTextColor(Color.parseColor(darkGrey));
        mAbout.setTextColor(Color.parseColor(darkGrey));
        mThemeBrownLabel.setTextColor(Color.parseColor(darkGrey));
        mThemeBlueLabel.setTextColor(Color.parseColor(darkGrey));
        mOffLabel.setTextColor(Color.parseColor(darkGrey));
        mMorningLabel.setTextColor(Color.parseColor(darkGrey));
        mEveningLabel.setTextColor(Color.parseColor(darkGrey));
    }

}
