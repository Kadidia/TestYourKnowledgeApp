package com.example.tyk.testyourknowledge;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.squareup.picasso.Picasso;

/**
 * Created by Axa on 30/01/2017.
 */

public class homePageAdapter extends ArrayAdapter<String> {
    private Context context;

    public homePageAdapter(Context context, List<String> datas){
        super(context, 0, datas);
        Log.i("tesr", "ok1");
        this.context = context;
        for(String s : datas){
            Log.i("datas", s);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowhomepage, parent, false);
        }
        ItemViewHolder itemViewHolder = (ItemViewHolder) convertView.getTag();
        if(itemViewHolder ==null){
            itemViewHolder = new ItemViewHolder();
            itemViewHolder.name = (TextView) convertView.findViewById(R.id.itemName);
            itemViewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(itemViewHolder);
        }
        String data = getItem(position);

       /* Picasso.with(context).load(data)
        .error(R.drawable.book)
                .placeholder(R.drawable.book)
                .into(itemViewHolder.image);*/
        itemViewHolder.name.setText(data);
        Log.i("kadi", itemViewHolder.name.getText().toString());

        switch (itemViewHolder.name.getText().toString()){
            case "Mes cours" : itemViewHolder.image.setImageResource(R.drawable.qcm4); break;
            case "Mes Quiz" : itemViewHolder.image.setImageResource(R.drawable.qcm6); break;
            case "Messagerie" : itemViewHolder.image.setImageResource(R.drawable.msg); break;
            case "Mes notes" : itemViewHolder.image.setImageResource(R.drawable.note); break;
            case "Statistiques Quiz" : itemViewHolder.image.setImageResource(R.drawable.stat); break;
            default:
                itemViewHolder.image.setImageResource(R.drawable.logo);
                break;


        }
        //itemViewHolder.image.setImageResource(R.drawable.logo);
        return convertView;
    }

    private class ItemViewHolder{
        public TextView name;
        public ImageView image;

    }
}
