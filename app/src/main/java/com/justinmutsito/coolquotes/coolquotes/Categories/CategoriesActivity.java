package com.justinmutsito.coolquotes.coolquotes.Categories;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.justinmutsito.coolquotes.coolquotes.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoriesActivity extends ListActivity {
    private String[] mCategories;
    private String mTheme;

    @Bind(R.id.backgroundImage) ImageView mBackgroundImage;
    @Bind(R.id.fadedImage) ImageView mFadedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        mTheme = getIntent().getStringExtra("ThemeKey");
        setMyTheme(mTheme);


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
        intent.putExtra("ThemeKey",mTheme);
        startActivity(intent);
    }

    private void setMyTheme(String theme){
        if(theme.equals("brown")){
            String darkGrey = "#212121";
            mBackgroundImage.setImageResource(R.drawable.brown_bg);
            mFadedImage.setImageResource(R.color.brownFaded);
        }
        else{
            String white = "#ffffff";
            mBackgroundImage.setImageResource(R.drawable.blue_bg);
            mFadedImage.setImageResource(R.color.blueFaded);
        }
    }

}
