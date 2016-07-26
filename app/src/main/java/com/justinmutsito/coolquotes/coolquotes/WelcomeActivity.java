package com.justinmutsito.coolquotes.coolquotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.justinmutsito.coolquotes.coolquotes.Authors.AuthorsActivity;
import com.justinmutsito.coolquotes.coolquotes.Categories.CategoriesActivity;
import com.justinmutsito.coolquotes.coolquotes.Database.DBOpenHelper;
import com.justinmutsito.coolquotes.coolquotes.Favourites.FavouritesActivity;
import com.justinmutsito.coolquotes.coolquotes.Settings.SettingsActivity;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class WelcomeActivity extends AppCompatActivity {


    private String[] mQuotes;
    private String mTheme;
    private int mPersonNumber;
    private int mPosition;
    private String mCurrentQuote;
    private DBOpenHelper mDBOpenHelper;

    @Bind(R.id.faceImageView)
    ImageView mFace;
    @Bind(R.id.quoteLabel)
    TextView mQuote;
    @Bind(R.id.categoriesButton)
    Button mCategories;
    @Bind(R.id.authorButton)
    Button mAuthor;
    @Bind(R.id.shareIcon)
    ImageView mIconShare;
    @Bind(R.id.favouritesLabel)
    TextView mFavorites;
    @Bind(R.id.settingsIcon)
    ImageView mIconSettings;
    @Bind(R.id.fadedImage)
    ImageView mFadedImage;
    @Bind(R.id.backgroundImage)
    ImageView mBackgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        //Set theme using mTheme passed from the SettingsActivity
        mTheme = getIntent().getStringExtra(getString(R.string.themeKey));
        setMyTheme(mTheme);

        //Get and set UI data
        randomQuote();
        getQuotes(mPersonNumber);
        mCurrentQuote = mQuotes[mPosition];
        mQuote.setText(mCurrentQuote);
        animateViews();

        //Open database for saving
        mDBOpenHelper = new DBOpenHelper(this);


    }

    private void animateViews() {
        //Animate views
        YoYo.with(Techniques.RollIn).duration(1000).playOn(mFadedImage);
        YoYo.with(Techniques.BounceIn).duration(2000).playOn(mFace);
        YoYo.with(Techniques.ZoomIn).duration(2000).playOn(mQuote);
        YoYo.with(Techniques.RollIn).duration(2000).playOn(mCategories);
        YoYo.with(Techniques.RollIn).duration(2000).playOn(mAuthor);
        YoYo.with(Techniques.ZoomIn).duration(2000).playOn(mIconShare);
        YoYo.with(Techniques.Tada).duration(2000).playOn(mFavorites);
        YoYo.with(Techniques.ZoomIn).duration(2000).playOn(mIconSettings);

    }


    @OnClick(R.id.quoteLabel)
    public void getQuote() {
        //Share or add quote to favourites

        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);

        builder.setItems(R.array.quotesOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (position == 0) {
                    //Share
                    share(mCurrentQuote);
                } else {
                    //Add to favourites
                    boolean added = mDBOpenHelper.addFavourite(mCurrentQuote);
                    if (added) {
                        Toast.makeText(WelcomeActivity.this, R.string.added, Toast.LENGTH_SHORT).show();


                    } else {
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(WelcomeActivity.this);
                        sweetAlertDialog.setTitleText(getString(R.string.not_added))
                                .show();

                    }

                }
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


    }


    @OnClick(R.id.categoriesButton)
    public void startActivityCategories() {
        Intent intent = new Intent(WelcomeActivity.this, CategoriesActivity.class);
        intent.putExtra(getString(R.string.themeKey), mTheme);
        startActivity(intent);

    }


    @OnClick(R.id.authorButton)
    public void startActivityPeople() {
        Intent intent = new Intent(WelcomeActivity.this, AuthorsActivity.class);
        intent.putExtra(getString(R.string.themeKey), mTheme);
        startActivity(intent);

    }

    @OnClick(R.id.shareIcon)
    public void shareAppDetails() {
        //Share app with friends by sending a its link to the app store

        String url = "https://play.google.com/store/apps/details?id=com.justinmutsito.standoutqoutes";
        String message = "Check out this cool app " + url;
        share(message);

    }

    @OnClick(R.id.favouritesLabel)
    public void startActivityFavourites() {
        Intent intent = new Intent(WelcomeActivity.this, FavouritesActivity.class);
        intent.putExtra(getString(R.string.themeKey), mTheme);
        startActivity(intent);

    }

    @OnClick(R.id.settingsIcon)
    public void configSettings() {
        Intent intent = new Intent(WelcomeActivity.this, SettingsActivity.class);
        intent.putExtra(getString(R.string.intentKey), "stay");
        startActivity(intent);
    }

    private void randomQuote() {

        //Sets randomly the person and quote position.

        int NUMBER_OF_PEOPLE = 23; //Number of people in the people string array
        int MINIMUM_NUMBER_OF_QUOTES = 10; //Minimum number of quotes provided by one person

        Random r = new Random();
        mPersonNumber = r.nextInt(NUMBER_OF_PEOPLE - 1);
        mPosition = r.nextInt(MINIMUM_NUMBER_OF_QUOTES - 1);

    }

    private void getQuotes(int n) {

        //Select which string array of quotes to use
        switch (n) {

            case 0: {
                mQuotes = getResources().getStringArray(R.array.Albert_Einstein);
                mFace.setImageResource(R.drawable.bg_albert);//Put authors's face
                break;
            }
            case 1: {
                mQuotes = getResources().getStringArray(R.array.Abraham_Lincoln);
                mFace.setImageResource(R.drawable.bg_abraham);
                break;
            }


            case 2: {
                mQuotes = getResources().getStringArray(R.array.Benjamin_Franklin);
                mFace.setImageResource(R.drawable.bg_benjamin);
                break;
            }

            case 3: {
                mQuotes = getResources().getStringArray(R.array.Bill_Gates);
                mFace.setImageResource(R.drawable.bg_bill_gates);
                break;
            }

            case 4: {
                mQuotes = getResources().getStringArray(R.array.Bill_Cosby);
                mFace.setImageResource(R.drawable.bg_bill_cosby);
                break;
            }

            case 5: {
                mQuotes = getResources().getStringArray(R.array.Confucius);
                mFace.setImageResource(R.drawable.bg_confucius);
                break;
            }
            case 6: {
                mQuotes = getResources().getStringArray(R.array.Charles_Darwin);
                mFace.setImageResource(R.drawable.bg_charles_darwin);
                break;
            }

            case 7: {
                mQuotes = getResources().getStringArray(R.array.Charles_Dickens);
                mFace.setImageResource(R.drawable.bg_charles_dickens);
                break;
            }

            case 8: {
                mQuotes = getResources().getStringArray(R.array.Charlie_Chaplin);
                mFace.setImageResource(R.drawable.bg_charlie_chaplin);
                break;
            }
            case 9: {
                mQuotes = getResources().getStringArray(R.array.Ernest_Hemingway);
                mFace.setImageResource(R.drawable.bg_ernest_hemingway);
                break;
            }

            case 10: {
                mQuotes = getResources().getStringArray(R.array.Ernesto_Guevara);
                mFace.setImageResource(R.drawable.bg_ernesto);
                break;
            }

            case 11: {
                mQuotes = getResources().getStringArray(R.array.George_Bernard_Shaw);
                mFace.setImageResource(R.drawable.bg_george_bernard);
                break;
            }
            case 12: {
                mQuotes = getResources().getStringArray(R.array.Henry_Ford);
                mFace.setImageResource(R.drawable.bg_henry_ford);
                break;
            }

            case 13: {
                mQuotes = getResources().getStringArray(R.array.Julian_Assange);
                mFace.setImageResource(R.drawable.bg_julian__assange);
                break;
            }
            case 14: {
                mQuotes = getResources().getStringArray(R.array.Karl_Marx);
                mFace.setImageResource(R.drawable.bg_karl_marx);
                break;
            }

            case 15: {
                mQuotes = getResources().getStringArray(R.array.Mahatma_Gandhi);
                mFace.setImageResource(R.drawable.bg_mahatma__gandhi);
                break;
            }
            case 16: {
                mQuotes = getResources().getStringArray(R.array.Mother_Teresa);
                mFace.setImageResource(R.drawable.bg_mother_teresa);
                break;
            }

            case 17: {
                mQuotes = getResources().getStringArray(R.array.Mark_Twain);
                mFace.setImageResource(R.drawable.bg_mark_twain);
                break;
            }

            case 18: {
                mQuotes = getResources().getStringArray(R.array.Oscar_Wilde);
                mFace.setImageResource(R.drawable.bg_oscar_wilde);
                break;
            }

            case 19: {
                mQuotes = getResources().getStringArray(R.array.Socrates);
                mFace.setImageResource(R.drawable.bg_socrates);
                break;
            }
            case 20: {
                mQuotes = getResources().getStringArray(R.array.Steven_Jobs);
                mFace.setImageResource(R.drawable.bg_steve_jobs);
                break;
            }

            case 21: {
                mQuotes = getResources().getStringArray(R.array.William_Shakespeare);
                mFace.setImageResource(R.drawable.bg_william_shakespeare);
                break;
            }


            default: {
                mQuotes = getResources().getStringArray(R.array.Warren_Buffett);
                mFace.setImageResource(R.drawable.bg_warren_buffet);

            }
        }

    }

    private void share(String text) {
        //Share text
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text + ".Shared from " + getString(R.string.app_name) + " .");
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
    }

    private void setMyTheme(String theme) {
        if (theme.equals("brown")) {
            String darkGrey = "#212121";
            mBackgroundImage.setImageResource(R.drawable.bg_brown);
            mFadedImage.setImageResource(R.color.brownFaded);
            mQuote.setTextColor(Color.parseColor(darkGrey));
            mCategories.setTextColor(Color.parseColor(darkGrey));
            mCategories.setBackground(getResources().getDrawable(R.drawable.bg_brown_circle_gradient));
            mAuthor.setTextColor(Color.parseColor(darkGrey));
            mAuthor.setBackground(getResources().getDrawable(R.drawable.bg_brown_circle_gradient));
            mIconShare.setImageResource(R.drawable.ic_share_variant_grey600_48dp);
            mFavorites.setTextColor(Color.parseColor(darkGrey));
            mIconSettings.setImageResource(R.drawable.ic_settings_grey600_48dp);

        } else {
            String white = "#ffffff";
            mBackgroundImage.setImageResource(R.drawable.bg_blue);
            mFadedImage.setImageResource(R.color.blueFaded);
            mQuote.setTextColor(Color.parseColor(white));
            mCategories.setTextColor(Color.parseColor(white));
            mCategories.setBackground(getResources().getDrawable(R.drawable.bg_blue_circle_gradient));
            mAuthor.setTextColor(Color.parseColor(white));
            mAuthor.setBackground(getResources().getDrawable(R.drawable.bg_blue_circle_gradient));
            mIconShare.setImageResource(R.drawable.ic_share_variant_white_48dp);
            mFavorites.setTextColor(Color.parseColor(white));
            mIconSettings.setImageResource(R.drawable.ic_settings_white_48dp);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        mDBOpenHelper.close();
    }
}


