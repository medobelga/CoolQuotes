package com.justinmutsito.coolquotes.coolquotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.justinmutsito.coolquotes.coolquotes.R;

/**
 * Created by justin on 7/14/16.
 */
public class AuthorsAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mAuthors;
    private int[] mAuthorFace;


    public AuthorsAdapter(Context context, String[] Authors, int[] AuthorFace) {
        mContext = context;
        mAuthors = Authors;
        mAuthorFace = AuthorFace;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mAuthors.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class ViewHolder {
        public TextView name;
        public ImageView face;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;


        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.author_layout, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.nameLabel);
            holder.face = (ImageView) convertView.findViewById(R.id.faceImageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(mAuthors[position]);
        holder.face.setImageResource(mAuthorFace[position]);

        return convertView;


    }
}