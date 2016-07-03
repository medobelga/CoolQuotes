package com.justinmutsito.coolquotes.coolquotes.Categories;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.justinmutsito.coolquotes.coolquotes.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends AppCompatActivity {
    private String[] mQuotes;
    private int count = 0;

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
        getSupportActionBar().hide();


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(getString(R.string.bundleKey));
        int position = bundle.getInt(getString(R.string.categoryKey));

        getQuotes(position);
        setQuotes();


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

    private void setQuotes() {
        //Set all the TextViews
        mQuote1.setText(mQuotes[count]);
        mQuote2.setText(mQuotes[count + 1]);
        mQuote3.setText(mQuotes[count + 2]);
        mQuote4.setText(mQuotes[count + 3]);
    }


    @OnClick(R.id.previousIcon)
    public void previous() {

        if (count == 0) {
            setQuotes();
        } else {
            count--;
            setQuotes();
        }
    }

    @OnClick(R.id.nextIcon)
    public void next() {

        count++;
        if (count + 3 <= mQuotes.length - 1) {
            setQuotes();
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
            setQuotes();
            endOfQuotes();


        }
    }


    private void endOfQuotes() {
        Toast.makeText(CategoryActivity.this, R.string.end, Toast.LENGTH_SHORT).show();
    }



}

