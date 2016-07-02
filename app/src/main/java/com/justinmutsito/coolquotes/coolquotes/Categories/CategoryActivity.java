package com.justinmutsito.coolquotes.coolquotes.Categories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.justinmutsito.coolquotes.coolquotes.R;

public class CategoryActivity extends AppCompatActivity {
    private int mPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().hide();



    }
}
