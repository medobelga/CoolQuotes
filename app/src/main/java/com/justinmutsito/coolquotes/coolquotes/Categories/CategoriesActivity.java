package com.justinmutsito.coolquotes.coolquotes.Categories;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startThisActivity(position);
    }

    private void startThisActivity(int position){
        Intent  intent = new Intent(CategoriesActivity.this,CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.categoryKey),position);
        intent.putExtra(getString(R.string.bundleKey),bundle);
        startActivity(intent);
    }


}
