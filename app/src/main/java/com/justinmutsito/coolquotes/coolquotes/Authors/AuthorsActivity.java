package com.justinmutsito.coolquotes.coolquotes.Authors;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.justinmutsito.coolquotes.coolquotes.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AuthorsActivity extends ListActivity {
    private String mTheme;

    @Bind(R.id.backgroundImage)
    ImageView mBackgroundImage;
    @Bind(R.id.fadedImage)
    ImageView mFadedImage;

    private int[] AuthorFaces = {R.drawable.ic_albert, R.drawable.ic_abraham, R.drawable.ic_benjamin, R.drawable.ic_bill_gates,
            R.drawable.ic_bill_cosby, R.drawable.ic_confucius, R.drawable.ic_charles_darwin,
            R.drawable.ic_charlse_dickens, R.drawable.ic_charlie_chaplin, R.drawable.ic_ernest_hemingway,
            R.drawable.ic_ernesto, R.drawable.ic_george_bernard, R.drawable.ic_henry_ford, R.drawable.ic_julian__assange,
            R.drawable.ic_karl_marx_, R.drawable.ic_mahatma__gandhi, R.drawable.ic_mother_teresa, R.drawable.ic_mark_twain,
            R.drawable.ic_oscar_wilde, R.drawable.ic_socrates, R.drawable.ic_steve_jobs, R.drawable.ic_william_shakespeare,
            R.drawable.ic_warren_buffet};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
        ButterKnife.bind(this);

        //Get and set current theme.
        mTheme = getIntent().getStringExtra(getString(R.string.themeKey));
        setMyTheme(mTheme);

        //Get authors data and adapt it for listview.
        String[] Authors = getResources().getStringArray(R.array.authors);
        AuthorsAdapter adapter = new AuthorsAdapter(this, Authors, AuthorFaces);
        setListAdapter(adapter);


    }

    private void setMyTheme(String theme) {

        if (theme.equals("brown")) {

            mBackgroundImage.setImageResource(R.drawable.brown_bg);
            mFadedImage.setImageResource(R.color.brownFaded);

        } else {

            mBackgroundImage.setImageResource(R.drawable.blue_bg);
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
        intent.putExtra(getString(R.string.themeKey), mTheme);
        startActivity(intent);

    }
}
