package com.justinmutsito.coolquotes.coolquotes.Categories;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.justinmutsito.coolquotes.coolquotes.R;
import com.justinmutsito.coolquotes.coolquotes.Settings.Preferences;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoriesActivity extends ListActivity {
    private Preferences mPreferences;
    private String[] mCategories;
    private String mTheme;

    @Bind(R.id.backgroundImage)
    ImageView mBackgroundImage;
    @Bind(R.id.fadedImage)
    ImageView mFadedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        mPreferences = new Preferences(this);
        mTheme = mPreferences.getMyTheme();
        setMyTheme(mTheme);

        mCategories = getResources().getStringArray(R.array.categories);
        CategoriesAdapter adapter = new CategoriesAdapter(this, mCategories);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startThisActivity(position);
    }

    private void startThisActivity(int position) {
        Intent intent = new Intent(CategoriesActivity.this, CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.categoryKey), position);
        intent.putExtra(getString(R.string.bundleKey), bundle);
        startActivity(intent);
    }

    private void setMyTheme(String theme) {
        if (theme.equals("brown")) {

            mBackgroundImage.setImageResource(R.drawable.bg_brown);
            mFadedImage.setImageResource(R.color.brownFaded);
        } else {

            mBackgroundImage.setImageResource(R.drawable.bg_blue);
            mFadedImage.setImageResource(R.color.blueFaded);
        }
    }

}
