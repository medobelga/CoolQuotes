package com.justinmutsito.coolquotes.coolquotes.Categories;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.justinmutsito.coolquotes.coolquotes.R;

public class CategoriesActivity extends ListActivity {
    private String[] mCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        mCategories = getResources().getStringArray(R.array.categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mCategories);
        setListAdapter(adapter);
    }





}
