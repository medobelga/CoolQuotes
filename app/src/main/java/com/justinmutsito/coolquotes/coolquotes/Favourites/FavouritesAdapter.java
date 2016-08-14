package com.justinmutsito.coolquotes.coolquotes.Favourites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.justinmutsito.coolquotes.coolquotes.R;

import java.util.ArrayList;

/**
 * Created by justin on 8/14/16.
 */
public class FavouritesAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mQuotes;

    public FavouritesAdapter(Context context,ArrayList<String> quotes) {
        mContext=context;
        mQuotes=quotes;

    }


    @Override
    public int getCount() {
        return mQuotes.size();
    }

    @Override
    public Object getItem(int position) {
        return mQuotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.favourites_layout,null);
            holder.quote = (TextView) convertView.findViewById(R.id.quoteLabel);
            convertView.setTag(holder);
        }

        else{
            holder= (ViewHolder) convertView.getTag();
        }


        holder.quote.setText(mQuotes.get(position));

        return convertView;
    }



    public  class ViewHolder{
       public  TextView quote;
    }


}
