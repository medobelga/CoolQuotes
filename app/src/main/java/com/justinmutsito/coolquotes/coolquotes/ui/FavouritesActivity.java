package com.justinmutsito.coolquotes.coolquotes.ui;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.justinmutsito.coolquotes.coolquotes.adapters.FavouritesAdapter;
import com.justinmutsito.coolquotes.coolquotes.database.DBOpenHelper;
import com.justinmutsito.coolquotes.coolquotes.R;
import com.justinmutsito.coolquotes.coolquotes.preferences.Preferences;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class FavouritesActivity extends ListActivity {
    private Preferences mPreferences;
    private String mTheme;
    private ArrayList<String> mFavourites;
    private DBOpenHelper mDBOpenHelper;
    private Bundle savedInstanceState;


    @Bind(R.id.backgroundImage)
    ImageView mBackgroundImage;
    @Bind(R.id.fadedImage)
    ImageView mFadedImage;
    @Bind(android.R.id.empty)
    TextView mEmptyLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        ButterKnife.bind(this);

        //Set theme.
        mPreferences = new Preferences(this);
        mTheme = mPreferences.getMyTheme();
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
            mEmptyLabel.setTextColor(Color.parseColor("#212121"));

        } else {

            mBackgroundImage.setImageResource(R.drawable.bg_blue);
            mFadedImage.setImageResource(R.color.blueFaded);
            mEmptyLabel.setTextColor(Color.parseColor("#ffffff"));


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

        FavouritesAdapter adapter = new FavouritesAdapter(FavouritesActivity.this,mFavourites);
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
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(FavouritesActivity.this, SweetAlertDialog.WARNING_TYPE);
                    sweetAlertDialog.setTitleText("Are you sure?")
                            .setConfirmText("Yes,delete it!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    mDBOpenHelper.delete(mFavourites.get(location));
                                    onCreate(savedInstanceState);
                                }
                            })
                            .show();


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




