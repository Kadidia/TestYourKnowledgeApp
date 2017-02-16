package com.example.tyk.testyourknowledge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shegd on 16/02/2017.
 */
public class QuizLookAdapter extends ArrayAdapter<String> {

    private Context context;
    public QuizLookAdapter(Context context, List<String> datas) {

        super(context,0, datas);

        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowlook, parent, false);

        }
        ItemViewHolder itemViewHolder = (ItemViewHolder) convertView.getTag();

        if(itemViewHolder == null){
            itemViewHolder = new ItemViewHolder();
            itemViewHolder.question = (TextView) convertView.findViewById(R.id.questionLook);
            itemViewHolder.response = (TextView) convertView.findViewById(R.id.responseLook);
           // itemViewHolder.response = (TextView) convertView.findViewById(R.id.responseLook);
        }

        String data = getItem(position);

        itemViewHolder.question.setText(data);
        itemViewHolder.response.setText(data);

        return  convertView;
    }

    private class ItemViewHolder {
        public TextView question;
        public TextView response;
       // public List<TextView>choice;
    }
}
