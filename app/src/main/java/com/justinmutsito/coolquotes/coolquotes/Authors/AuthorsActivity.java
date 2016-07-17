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

    private int[] AuthorFaces = {R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp
            , R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp
            , R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp,
            R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
        ButterKnife.bind(this);
        mTheme = getIntent().getStringExtra(getString(R.string.themeKey));

        setMyTheme(mTheme);
        String[] Authors = getResources().getStringArray(R.array.authors);
        AuthorsAdapter adapter = new AuthorsAdapter(this, Authors, AuthorFaces);
        setListAdapter(adapter);


    }

    private void setMyTheme(String theme) {

        if (theme.equals("brown")) {
            String darkGrey = "#212121";

            mBackgroundImage.setImageResource(R.drawable.brown_bg);
            mFadedImage.setImageResource(R.color.brownFaded);

        } else {
            String white = "#ffffff";
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
