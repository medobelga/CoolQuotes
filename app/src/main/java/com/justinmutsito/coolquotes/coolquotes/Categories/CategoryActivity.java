package com.justinmutsito.coolquotes.coolquotes.Categories;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.justinmutsito.coolquotes.coolquotes.R;

public class CategoryActivity extends AppCompatActivity {
    private int mPosition;
    private String[] mQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().hide();


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(getString(R.string.bundleKey));
        mPosition = bundle.getInt(getString(R.string.categoryKey));

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
}

