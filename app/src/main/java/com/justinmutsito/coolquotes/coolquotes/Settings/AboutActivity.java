package com.justinmutsito.coolquotes.coolquotes.Settings;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinmutsito.coolquotes.coolquotes.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {
    private String mTheme;

    @Bind(R.id.backgroundImage)
    ImageView mBackgroundImage;
    @Bind(R.id.fadedImage)
    ImageView mFadedImage;
    @Bind(R.id.aboutLabel)
    TextView mAboutLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        mTheme = getIntent().getStringExtra(getString(R.string.themeKey));
        setMyTheme(mTheme);
    }


    private void setMyTheme(String theme) {

        if (theme.equals("brown")) {
            String darkGrey = "#212121";

            mBackgroundImage.setImageResource(R.drawable.brown_bg);
            mFadedImage.setImageResource(R.color.brownFaded);
            mAboutLabel.setTextColor(Color.parseColor(darkGrey));

        } else {
            String white = "#ffffff";
            mBackgroundImage.setImageResource(R.drawable.blue_bg);
            mFadedImage.setImageResource(R.color.blueFaded);
            mAboutLabel.setTextColor(Color.parseColor(white));


        }
    }
}
