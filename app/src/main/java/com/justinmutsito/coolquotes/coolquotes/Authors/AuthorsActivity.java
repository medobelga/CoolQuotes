package com.justinmutsito.coolquotes.coolquotes.Authors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.justinmutsito.coolquotes.coolquotes.R;

import butterknife.ButterKnife;

public class AuthorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
        ButterKnife.bind(this);




    }
}
