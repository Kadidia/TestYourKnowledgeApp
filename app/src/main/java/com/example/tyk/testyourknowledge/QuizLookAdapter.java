package com.example.tyk.testyourknowledge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shegd on 16/02/2017.
 */
public class QuizLookAdapter extends ArrayAdapter<Question> {

    private Context context;
    public QuizLookAdapter(Context context, List<Question> datas) {

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
            itemViewHolder.choice1 = (TextView) convertView.findViewById(R.id.choice1Look);
            itemViewHolder.choice2 = (TextView) convertView.findViewById(R.id.choice2Look);
           // itemViewHolder.choice = (ListView) convertView.findViewById(R.id.lookListView);
        }

        Question data = getItem(position);

        itemViewHolder.question.setText(data.getQuestion().toString());
        itemViewHolder.response.setText(data.getResponse().toString());
        itemViewHolder.choice1.setText(data.getChoices().get(0).toString());
        itemViewHolder.choice2.setText(data.getChoices().get(1).toString());
       /* ArrayAdapter arrayAdapter;
        ListView choiceView =null;
        choiceView = (ListView) convertView.findViewById(R.id.lookListView);
        arrayAdapter = new ArrayAdapter<String>(getContext(),0,data.getChoices() );
        choiceView.setAdapter(arrayAdapter);

        itemViewHolder.choice.setAdapter(arrayAdapter);*/

        return  convertView;
    }

    private class ItemViewHolder {
        public TextView question;
        public TextView response;
        public TextView choice1;
        public TextView choice2;
        //public ListView choice;
    }
}
