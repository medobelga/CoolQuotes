package com.justinmutsito.coolquotes.coolquotes.Favourites;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.justinmutsito.coolquotes.coolquotes.Database.DBOpenHelper;
import com.justinmutsito.coolquotes.coolquotes.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FavouritesActivity extends ListActivity {
    private String mTheme;
    private ArrayList<String> mFavourites;
    private DBOpenHelper mDBOpenHelper;
    private Bundle savedInstanceState;


    @Bind(R.id.backgroundImage)
    ImageView mBackgroundImage;
    @Bind(R.id.fadedImage)
    ImageView mFadedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        ButterKnife.bind(this);
//Set theme.
        mTheme = getIntent().getStringExtra(getString(R.string.themeKey));
        setMyTheme(mTheme);
//Start database operations.
        mFavourites = new ArrayList<>();
        mDBOpenHelper = new DBOpenHelper(FavouritesActivity.this);

//Load UI.
        loadUi();


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

    private void loadUi() {
        //Get favourites quotes from the database and adapt the data for the listview.
        Cursor cursor = mDBOpenHelper.getFavourites();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int columnIndex = cursor.getColumnIndex(mDBOpenHelper.COLUMN_FAVOURITE);
            String quote = cursor.getString(columnIndex);
            mFavourites.add(quote);
            cursor.moveToNext();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mFavourites);
        setListAdapter(adapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        quoteOptions(position);
    }


    private void share(String text) {
        //Share quote.
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text + ".Shared from " + getString(R.string.app_name) + " .");
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
    }

    private void quoteOptions(final int location) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FavouritesActivity.this);

        builder.setItems(R.array.shareOrDelete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (position == 0) {
                    //Share
                    share(mFavourites.get(location));
                } else {
                    //Delete

                    mDBOpenHelper.delete(mFavourites.get(location));
                    onCreate(savedInstanceState);


                }
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDBOpenHelper.close();
    }

}




