package com.justinmutsito.coolquotes.coolquotes.Categories;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.justinmutsito.coolquotes.coolquotes.Database.DBOpenHelper;
import com.justinmutsito.coolquotes.coolquotes.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class CategoryActivity extends AppCompatActivity {
    private String[] mQuotes;
    private String mTheme;
    private int count = 0;
    private DBOpenHelper mDBOpenHelper;


    @Bind(R.id.backgroundImage)
    ImageView mBackgroundImage;
    @Bind(R.id.fadedImage)
    ImageView mFadedImage;
    @Bind(R.id.quote1Label)
    TextView mQuote1;
    @Bind(R.id.quote2Label)
    TextView mQuote2;
    @Bind(R.id.quote3Label)
    TextView mQuote3;
    @Bind(R.id.quote4Label)
    TextView mQuote4;
    @Bind(R.id.layout1)
    LinearLayout mLayout;
    @Bind(R.id.previousIcon)
    ImageView mPrevious;
    @Bind(R.id.jumpToLabel)
    TextView mJumpTo;
    @Bind(R.id.nextIcon)
    ImageView mNext;
    @Bind(R.id.quoteCount)
    TextView mQuoteCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(getString(R.string.bundleKey));
        int position = bundle.getInt(getString(R.string.categoryKey));
        mTheme = intent.getStringExtra(getString(R.string.themeKey));
        setMyTheme(mTheme);
        getQuotes(position);
        setQuotes(count);

        mDBOpenHelper = new DBOpenHelper(this);
    }


    private void getQuotes(int position) {

        //Select which string array of quotes to use
        switch (position) {

            case 0: {
                mQuotes = getResources().getStringArray(R.array.Books);
                break;
            }

            case 1: {
                mQuotes = getResources().getStringArray(R.array.Business);
                break;
            }


            case 2: {
                mQuotes = getResources().getStringArray(R.array.Dreams);
                break;
            }

            case 3: {
                mQuotes = getResources().getStringArray(R.array.Funny);
                break;
            }

            case 4: {
                mQuotes = getResources().getStringArray(R.array.Humour);
                break;
            }

            case 5: {
                mQuotes = getResources().getStringArray(R.array.Humanity);
                break;
            }
            case 6: {
                mQuotes = getResources().getStringArray(R.array.Inspiration);
                break;
            }

            case 7: {
                mQuotes = getResources().getStringArray(R.array.Success);
                break;
            }
            case 8: {
                mQuotes = getResources().getStringArray(R.array.Teamwork);
                break;
            }

            default: {
                mQuotes = getResources().getStringArray(R.array.Love);
            }
        }


    }

    private void setQuotes(int count) {

        mQuote1.setText(mQuotes[count]);
        mQuote2.setText(mQuotes[count + 1]);
        mQuote3.setText(mQuotes[count + 2]);
        mQuote4.setText(mQuotes[count + 3]);

        String countDisplay = (count) + "/" + mQuotes.length;
        mQuoteCount.setText(countDisplay);

        animateViews();

    }


    @OnClick(R.id.previousIcon)
    public void previous() {

        if (count == 0) {
            setQuotes(count);
        } else {
            count--;
            setQuotes(count);
        }
    }

    @OnClick(R.id.nextIcon)
    public void next() {

        count++;
        if (count + 3 <= mQuotes.length - 1) {
        } else if (mQuotes.length - count == 2) {

            mQuote1.setText(mQuotes[count]);
            mQuote2.setText(mQuotes[count + 1]);
            mQuote3.setText(mQuotes[count + 2]);
            animateViews();
            endOfQuotes();

        } else if (mQuotes.length - count == 1) {

            mQuote1.setText(mQuotes[count]);
            mQuote2.setText(mQuotes[count + 1]);
            animateViews();
            endOfQuotes();

        } else {
            count--;
            //setQuotes(count);
            endOfQuotes();


        }
    }


    @OnClick(R.id.quote1Label)
    public void useQuote1() {
        //Share or add quote to favourites
        quoteOptions(count);

    }

    @OnClick(R.id.quote2Label)
    public void useQuote2() {
        quoteOptions(count + 1);
    }

    @OnClick(R.id.quote3Label)
    public void useQuote3() {
        quoteOptions(count + 2);
    }

    @OnClick(R.id.quote4Label)
    public void useQuote4() {
        quoteOptions(count + 3);
    }

    @OnClick(R.id.jumpToLabel)
    public void jumpTo() {
        //Jump to quote number
        View dialogView = getLayoutInflater().inflate(R.layout.jump_to_layout, null);
        final EditText numberText = (EditText) dialogView.findViewById(R.id.numberField);

        AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this);
        builder.setView(dialogView)
                .setPositiveButton("Go", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String number = numberText.getText().toString();
                        goTo(number);

                    }
                }).setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void endOfQuotes() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(CategoryActivity.this, SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setTitleText(getString(R.string.Oops))
                .setContentText(getString(R.string.end))
                .show();
    }


    private void share(String text) {
        //Share text
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text + ".Shared from " + getString(R.string.app_name) + " .");
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
    }

    private void quoteOptions(final int location) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this);

        builder.setItems(R.array.quotesOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (position == 0) {
                    //Share
                    share(mQuotes[location]);
                } else {
                    //Add to favourites
                    boolean added = mDBOpenHelper.addFavourite(mQuotes[location]);
                    if (added) {

                        Toast.makeText(CategoryActivity.this, R.string.added, Toast.LENGTH_SHORT).show();
                    } else {
                        errorDialog();
                    }
                }
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void goTo(String number) {
        int goTo;

        try {
            goTo = Integer.parseInt(number);
            if ((goTo < mQuotes.length - 3)) {
                setQuotes(goTo);
            } else {
                errorDialog();
            }
        } catch (NumberFormatException e) {
            errorDialog();
        }

    }

    private void errorDialog() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(CategoryActivity.this, SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setTitleText(getString(R.string.Oops))
                .setContentText(getString(R.string.try_again))
                .show();
    }

    private void setMyTheme(String theme) {
        if (theme.equals("brown")) {
            String darkGrey = "#212121";
            mBackgroundImage.setImageResource(R.drawable.bg_brown);
            mFadedImage.setImageResource(R.color.brownFaded);
            mQuote1.setTextColor(Color.parseColor(darkGrey));
            mQuote2.setTextColor(Color.parseColor(darkGrey));
            mQuote3.setTextColor(Color.parseColor(darkGrey));
            mQuote4.setTextColor(Color.parseColor(darkGrey));
            mQuoteCount.setTextColor(Color.parseColor(darkGrey));
            mJumpTo.setTextColor(Color.parseColor(darkGrey));
            mPrevious.setImageResource(R.drawable.ic_skip_previous_circle_outline_grey600_48dp);
            mNext.setImageResource(R.drawable.ic_skip_next_circle_outline_grey600_48dp);
        } else {
            String white = "#ffffff";

            mBackgroundImage.setImageResource(R.drawable.bg_blue);
            mFadedImage.setImageResource(R.color.blueFaded);
            mQuote1.setTextColor(Color.parseColor(white));
            mQuote2.setTextColor(Color.parseColor(white));
            mQuote3.setTextColor(Color.parseColor(white));
            mQuote4.setTextColor(Color.parseColor(white));
            mQuoteCount.setTextColor(Color.parseColor(white));
            mJumpTo.setTextColor(Color.parseColor(white));
            mPrevious.setImageResource(R.drawable.ic_skip_previous_circle_outline_white_48dp);
            mNext.setImageResource(R.drawable.ic_skip_next_circle_outline_white_48dp);
        }

    }


    private void animateViews() {
        //Animate views
        YoYo.with(Techniques.RollIn).duration(1000).playOn(mFadedImage);
        YoYo.with(Techniques.BounceIn).duration(2000).playOn(mQuote1);
        YoYo.with(Techniques.BounceIn).duration(2300).playOn(mQuote2);
        YoYo.with(Techniques.BounceIn).duration(2600).playOn(mQuote3);
        YoYo.with(Techniques.BounceIn).duration(2900).playOn(mQuote4);
        YoYo.with(Techniques.Tada).duration(3000).playOn(mQuoteCount);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDBOpenHelper.close();
    }
}

