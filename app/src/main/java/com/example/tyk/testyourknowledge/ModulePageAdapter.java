package com.example.tyk.testyourknowledge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Axa on 14/02/2017.
 */

public class ModulePageAdapter extends ArrayAdapter<Module> {
    private Context context;

    public ModulePageAdapter(Context context, List<Module> datas) {
        super(context, 0, datas);
        this.context = context;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowhomepage, parent, false);
        }
        ModulePageAdapter.ItemViewHolder itemViewHolder = (ModulePageAdapter.ItemViewHolder) convertView.getTag();

        if(itemViewHolder == null){
            itemViewHolder = new ModulePageAdapter.ItemViewHolder();
            itemViewHolder.courseName = (TextView) convertView.findViewById(R.id.itemName);
            itemViewHolder.courseImage = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(itemViewHolder);
        }
        Module data = getItem(position);

        itemViewHolder.courseName.setText(data.getName());
        itemViewHolder.courseImage.setImageResource(R.drawable.book);
        itemViewHolder.courseImage.setColorFilter(ContextCompat.getColor(context,R.color.turquoise));


        return convertView;
    }



    private class ItemViewHolder {
        public TextView courseName;
        public ImageView  courseImage;
    }

}
