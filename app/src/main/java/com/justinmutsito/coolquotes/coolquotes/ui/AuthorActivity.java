package com.justinmutsito.coolquotes.coolquotes.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.justinmutsito.coolquotes.coolquotes.database.DBOpenHelper;
import com.justinmutsito.coolquotes.coolquotes.R;
import com.justinmutsito.coolquotes.coolquotes.preferences.Preferences;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class AuthorActivity extends AppCompatActivity {
    private String[] mQuotes;
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
        setContentView(R.layout.activity_author);
        ButterKnife.bind(this);

        //Get and set the current theme.
        Preferences preferences = new Preferences(this);
        String theme = preferences.getMyTheme();
        setMyTheme(theme);

        //Get quotes.
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(getString(R.string.authorsKey));
        int position = bundle.getInt(getString(R.string.bundleKey));
        getQuotes(position);

        //Set quotes.
        setQuotes(count);

        //Animate views.
        animateViews();

        //Open database for related operations.
        mDBOpenHelper = new DBOpenHelper(this);
    }

    private void getQuotes(int n) {

        //Select which string array of quotes to use
        switch (n) {

            case 0: {
                mQuotes = getResources().getStringArray(R.array.Albert_Einstein);
                break;
            }
            case 1: {
                mQuotes = getResources().getStringArray(R.array.Abraham_Lincoln);
                break;
            }


            case 2: {
                mQuotes = getResources().getStringArray(R.array.Benjamin_Franklin);
                break;
            }

            case 3: {
                mQuotes = getResources().getStringArray(R.array.Bill_Gates);
                break;
            }

            case 4: {
                mQuotes = getResources().getStringArray(R.array.Bill_Cosby);
                break;
            }

            case 5: {
                mQuotes = getResources().getStringArray(R.array.Confucius);
                break;
            }
            case 6: {
                mQuotes = getResources().getStringArray(R.array.Charles_Darwin);
                break;
            }

            case 7: {
                mQuotes = getResources().getStringArray(R.array.Charles_Dickens);
                break;
            }

            case 8: {
                mQuotes = getResources().getStringArray(R.array.Charlie_Chaplin);
                break;
            }
            case 9: {
                mQuotes = getResources().getStringArray(R.array.Ernest_Hemingway);
                break;
            }

            case 10: {
                mQuotes = getResources().getStringArray(R.array.Ernesto_Guevara);
                break;
            }

            case 11: {
                mQuotes = getResources().getStringArray(R.array.George_Bernard_Shaw);
                break;
            }
            case 12: {
                mQuotes = getResources().getStringArray(R.array.Henry_Ford);
                break;
            }

            case 13: {
                mQuotes = getResources().getStringArray(R.array.Julian_Assange);
                break;
            }
            case 14: {
                mQuotes = getResources().getStringArray(R.array.Karl_Marx);
                break;
            }

            case 15: {
                mQuotes = getResources().getStringArray(R.array.Mahatma_Gandhi);
                break;
            }
            case 16: {
                mQuotes = getResources().getStringArray(R.array.Mother_Teresa);
                break;
            }

            case 17: {
                mQuotes = getResources().getStringArray(R.array.Mark_Twain);
                break;
            }

            case 18: {
                mQuotes = getResources().getStringArray(R.array.Oscar_Wilde);
                break;
            }

            case 19: {
                mQuotes = getResources().getStringArray(R.array.Socrates);
                break;
            }
            case 20: {
                mQuotes = getResources().getStringArray(R.array.Steven_Jobs);
                break;
            }

            case 21: {
                mQuotes = getResources().getStringArray(R.array.William_Shakespeare);
                break;
            }


            default: {
                mQuotes = getResources().getStringArray(R.array.Warren_Buffett);

            }
        }

    }


    private void setQuotes(int count) {
        //Set all the TextViews
        mQuote1.setText(mQuotes[count]);
        mQuote2.setText(mQuotes[count + 1]);
        mQuote3.setText(mQuotes[count + 2]);
        mQuote4.setText(mQuotes[count + 3]);

        String countDisplay = (count + 4) + "/" + mQuotes.length;
        mQuoteCount.setText(countDisplay);

    }


    @OnClick(R.id.previousIcon)
    public void previous() {

        if (count == 0) {
            setQuotes(count);

        } else {
            count -= 4;
            setQuotes(count);
            animateViews();
        }
    }

    @OnClick(R.id.nextIcon)
    public void next() {

        count += 4;

        if (count + 3 <= mQuotes.length - 1) {
            setQuotes(count);


        } else if (count + 2 <= mQuotes.length - 1) {

            setQuotes(count -= 1);


        } else if (count + 1 <= mQuotes.length - 1) {

            setQuotes(count -= 2);


        } else if (count == mQuotes.length - 1) {

            setQuotes(count -= 3);


        } else {
            count -= 4;
            endOfQuotes();


        }
        animateViews();
    }


    @OnClick(R.id.jumpToLabel)
    public void jumpTo() {
        //Jump to quote number
        View dialogView = getLayoutInflater().inflate(R.layout.jump_to_layout, null);
        final EditText numberText = (EditText) dialogView.findViewById(R.id.numberField);

        AlertDialog.Builder builder = new AlertDialog.Builder(AuthorActivity.this);
        builder.setView(dialogView)
                .setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String number = numberText.getText().toString();
                        goTo(number);

                    }
                }).setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();

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


    private void endOfQuotes() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AuthorActivity.this, SweetAlertDialog.ERROR_TYPE);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AuthorActivity.this);

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

                        Toast.makeText(AuthorActivity.this, R.string.added, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AuthorActivity.this, R.string.not_added, Toast.LENGTH_SHORT).show();

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

            if ((goTo <= mQuotes.length)) {

                if (goTo <= 4) {
                    setQuotes(goTo);
                } else {
                    setQuotes(goTo - 4);
                }

                animateViews();
                count = 0; //To avoid next or previous  button ArrayIndexOutOfBoundsException

            } else {
                errorDialog();
            }

        } catch (NumberFormatException e) {
            errorDialog();
        }

    }

    private void errorDialog() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AuthorActivity.this, SweetAlertDialog.ERROR_TYPE);
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
        YoYo.with(Techniques.Bounce).duration(1000).playOn(mFadedImage);
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
