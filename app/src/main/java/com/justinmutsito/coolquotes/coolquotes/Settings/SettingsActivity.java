package com.justinmutsito.coolquotes.coolquotes.Settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.justinmutsito.coolquotes.coolquotes.R;

import at.markushi.ui.CircleButton;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @Bind(R.id.brownCheckbox) CircleButton mBrown;
    @Bind(R.id.blueCheckbox) CircleButton mBlue;
    @Bind(R.id.morningCheckbox) CircleButton mMorning;
    @Bind(R.id.eveningCheckbox) CircleButton mEvening;
    @Bind(R.id.aboutButton) Button mAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }
}
