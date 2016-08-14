package com.justinmutsito.coolquotes.coolquotes.Authors;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.justinmutsito.coolquotes.coolquotes.Adapters.AuthorsAdapter;
import com.justinmutsito.coolquotes.coolquotes.R;
import com.justinmutsito.coolquotes.coolquotes.Settings.Preferences;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AuthorsActivity extends ListActivity {
    private Preferences mPreferences;
    private String mTheme;

    @Bind(R.id.backgroundImage)
    ImageView mBackgroundImage;
    @Bind(R.id.fadedImage)
    ImageView mFadedImage;

    private int[] AuthorFaces = {R.drawable.bg_albert, R.drawable.bg_abraham, R.drawable.bg_benjamin, R.drawable.bg_bill_gates,
            R.drawable.bg_bill_cosby, R.drawable.bg_confucius, R.drawable.bg_charles_darwin,
            R.drawable.bg_charles_dickens, R.drawable.bg_charlie_chaplin, R.drawable.bg_ernest_hemingway,
            R.drawable.bg_ernesto, R.drawable.bg_george_bernard, R.drawable.bg_henry_ford, R.drawable.bg_julian__assange,
            R.drawable.bg_karl_marx, R.drawable.bg_mahatma__gandhi, R.drawable.bg_mother_teresa, R.drawable.bg_mark_twain,
            R.drawable.bg_oscar_wilde, R.drawable.bg_socrates, R.drawable.bg_steve_jobs, R.drawable.bg_william_shakespeare,
            R.drawable.bg_warren_buffet};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
        ButterKnife.bind(this);

        //Get and set current theme.;
        mPreferences = new Preferences(this);
        mTheme = mPreferences.getMyTheme();
        setMyTheme(mTheme);

        //Get authors data and adapt it for listView.
        String[] Authors = getResources().getStringArray(R.array.authors);
        AuthorsAdapter adapter = new AuthorsAdapter(this, Authors, AuthorFaces);
        setListAdapter(adapter);


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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startActivity(position);
    }

    private void startActivity(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.bundleKey), position);

        Intent intent = new Intent(AuthorsActivity.this, AuthorActivity.class);
        intent.putExtra(getString(R.string.authorsKey), bundle);
        startActivity(intent);

    }
}
