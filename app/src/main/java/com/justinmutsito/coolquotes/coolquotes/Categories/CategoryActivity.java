package com.justinmutsito.coolquotes.coolquotes.Categories;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.justinmutsito.coolquotes.coolquotes.Database.DBOpenHelper;
import com.justinmutsito.coolquotes.coolquotes.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends AppCompatActivity {
    private String[] mQuotes;
    private int count = 0;
    private DBOpenHelper mDBOpenHelper;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        mDBOpenHelper = new DBOpenHelper(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(getString(R.string.bundleKey));
        int position = bundle.getInt(getString(R.string.categoryKey));

        getQuotes(position);
        setQuotes(count);


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

        mQuote1.setText(mQuotes[count] );
        mQuote2.setText(mQuotes[count + 1]);
        mQuote3.setText(mQuotes[count + 2]);
        mQuote4.setText(mQuotes[count + 3]);
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
            setQuotes(count);
        } else if (mQuotes.length - count == 2) {

            mQuote1.setText(mQuotes[count]);
            mQuote2.setText(mQuotes[count + 1]);
            mQuote3.setText(mQuotes[count + 2]);
            endOfQuotes();

        } else if (mQuotes.length - count == 1) {

            mQuote1.setText(mQuotes[count]);
            mQuote2.setText(mQuotes[count + 1]);
            endOfQuotes();

        } else {
            count--;
            setQuotes(count);
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
                        String number =numberText.getText().toString();
                        goTo(number);

                    }
                }).setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void endOfQuotes() {
        Toast.makeText(CategoryActivity.this, R.string.end, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(CategoryActivity.this, R.string.not_added, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void goTo(String number) {
        int goTo = Integer.parseInt(number);
        if(goTo< mQuotes.length - 3){
            setQuotes(goTo);
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        mDBOpenHelper.close();
    }
}

