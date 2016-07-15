package com.justinmutsito.coolquotes.coolquotes.Authors;

import android.app.ListActivity;
import android.os.Bundle;

import com.justinmutsito.coolquotes.coolquotes.R;

public class AuthorsActivity extends ListActivity {

    public int[] AuthorFaces = {R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp
            , R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp
            , R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp,
            R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
        String[] Authors = getResources().getStringArray(R.array.authors);

        AuthorsAdapter adapter = new AuthorsAdapter(this, Authors, AuthorFaces);
        setListAdapter(adapter);

    }


}
