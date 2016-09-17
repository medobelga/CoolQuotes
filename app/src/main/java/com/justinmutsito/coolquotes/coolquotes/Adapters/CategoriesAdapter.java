package com.justinmutsito.coolquotes.coolquotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.justinmutsito.coolquotes.coolquotes.R;

/**
 * Created by justin on 8/14/16.
 */
public class CategoriesAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mCategories;

    public CategoriesAdapter(Context context, String[] categories) {
        mContext = context;
        mCategories = categories;
    }

    @Override
    public int getCount() {
        return mCategories.length;
    }

    @Override
    public Object getItem(int position) {
        return mCategories[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.categories_layout,null);
            holder.category = (TextView) convertView.findViewById(R.id.categoryLabel);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.category.setText(mCategories[position]);

        return convertView;
    }


    public class ViewHolder{
        public TextView category;
    }

}
