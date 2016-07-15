package com.justinmutsito.coolquotes.coolquotes.Authors;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.justinmutsito.coolquotes.coolquotes.R;

import butterknife.ButterKnife;

public class AuthorsActivity extends ListActivity {


    private int[] AuthorFaces = {R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp
            , R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp
            , R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp,
            R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp, R.drawable.ic_account_circle_grey600_48dp};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
        ButterKnife.bind(this);
        String[] Authors = getResources().getStringArray(R.array.authors);

        AuthorsAdapter adapter = new AuthorsAdapter(this, Authors, AuthorFaces);
        setListAdapter(adapter);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startActivity(position);
    }

    private void startActivity(int position){

                Bundle bundle = new Bundle();
                Intent intent = new Intent(AuthorsActivity.this, AuthorActivity.class);
                bundle.putInt(getString(R.string.bundleKey), position);
                intent.putExtra(getString(R.string.authorsKey), bundle);
                startActivity(intent);

    }
}
