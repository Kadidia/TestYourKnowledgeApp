package com.example.tyk.testyourknowledge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shegd on 13/02/2017.
 */

public class CoursePageAdapter extends ArrayAdapter<String> {

    private  Context context;

    public CoursePageAdapter(Context context, List<String> datas) {

        super(context, 0, datas);    Log.i("test", "ok1");

        this.context = context;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowhomepage, parent, false);
        }
        ItemViewHolder itemViewHolder = (ItemViewHolder) convertView.getTag();

        if(itemViewHolder == null){
            itemViewHolder = new ItemViewHolder();
            itemViewHolder.courseName = (TextView) convertView.findViewById(R.id.itemName);
            itemViewHolder.courseImage = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(itemViewHolder);
        }
        String data = getItem(position);

        itemViewHolder.courseName.setText(data);
        itemViewHolder.courseImage.setImageResource(R.drawable.book);

        return convertView;
    }



    private class ItemViewHolder {
        public TextView courseName;
        public ImageView  courseImage;
    }
}
