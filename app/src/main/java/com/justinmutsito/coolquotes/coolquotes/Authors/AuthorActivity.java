package com.justinmutsito.coolquotes.coolquotes.Authors;

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

public class AuthorActivity extends AppCompatActivity {
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
    @Bind(R.id.faceImageView)
    ImageView mFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        ButterKnife.bind(this);



        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(getString(R.string.authorsKey));
        int position = bundle.getInt(getString(R.string.bundleKey));
        getQuotes(position);
        setQuotes(count);

        mDBOpenHelper = new DBOpenHelper(this);
    }


    private void getQuotes(int n) {

        //Select which string array of quotes to use
        switch (n) {

            case 0: {
                mQuotes = getResources().getStringArray(R.array.Albert_Einstein);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }
            case 1: {
                mQuotes = getResources().getStringArray(R.array.Abraham_Lincoln);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }


            case 2: {
                mQuotes = getResources().getStringArray(R.array.Benjamin_Franklin);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 3: {
                mQuotes = getResources().getStringArray(R.array.Bill_Gates);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 4: {
                mQuotes = getResources().getStringArray(R.array.Bill_Cosby);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 5: {
                mQuotes = getResources().getStringArray(R.array.Confucius);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }
            case 6: {
                mQuotes = getResources().getStringArray(R.array.Charles_Darwin);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 7: {
                mQuotes = getResources().getStringArray(R.array.Charles_Dickens);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 8: {
                mQuotes = getResources().getStringArray(R.array.Charlie_Chaplin);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }
            case 9: {
                mQuotes = getResources().getStringArray(R.array.Ernest_Hemingway);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 10: {
                mQuotes = getResources().getStringArray(R.array.Ernesto_Guevara);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 11: {
                mQuotes = getResources().getStringArray(R.array.George_Bernard_Shaw);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }
            case 12: {
                mQuotes = getResources().getStringArray(R.array.Henry_Ford);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 13: {
                mQuotes = getResources().getStringArray(R.array.Julian_Assange);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }
            case 14: {
                mQuotes = getResources().getStringArray(R.array.Karl_Marx);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 15: {
                mQuotes = getResources().getStringArray(R.array.Mahatma_Gandhi);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }
            case 16: {
                mQuotes = getResources().getStringArray(R.array.Mother_Teresa);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 17: {
                mQuotes = getResources().getStringArray(R.array.Mark_Twain);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 18: {
                mQuotes = getResources().getStringArray(R.array.Oscar_Wilde);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 19: {
                mQuotes = getResources().getStringArray(R.array.Socrates);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }
            case 20: {
                mQuotes = getResources().getStringArray(R.array.Steven_Jobs);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }

            case 21: {
                mQuotes = getResources().getStringArray(R.array.William_Shakespeare);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face
                break;
            }


            default: {
                mQuotes = getResources().getStringArray(R.array.Warren_Buffett);
                mFace.setImageResource(R.drawable.ic_account_circle_white_48dp);//Put person's face

            }
        }

    }


    private void setQuotes(int count) {
        //Set all the TextViews
        mQuote1.setText(mQuotes[count]);
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

    @OnClick(R.id.jumpToLabel)
    public void jumpTo() {
        //Jump to quote number
        View dialogView = getLayoutInflater().inflate(R.layout.jump_to_layout, null);
        final EditText numberText = (EditText) dialogView.findViewById(R.id.numberField);

        AlertDialog.Builder builder = new AlertDialog.Builder(AuthorActivity.this);
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
        Toast.makeText(AuthorActivity.this, R.string.end, Toast.LENGTH_SHORT).show();
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
        int goTo = Integer.parseInt(number);
        if (goTo < mQuotes.length - 3) {
            setQuotes(goTo);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDBOpenHelper.close();
    }
}
