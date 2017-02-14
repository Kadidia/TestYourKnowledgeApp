package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shegd on 13/02/2017.
 */
public class NewQuizzPage extends Activity {

    ListView quizListView = null;
    NewQuizzPageAdapter newQuizzPageAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newquiz);

        quizListView = (ListView) findViewById(R.id.quizListView);

        String[] quiz = new String[]{
                "quiz 1",
                "quiz 2",
                "quiz 3"
        };
        List<String> listequiz = new ArrayList<String>();
        for(int i=0; i< quiz.length; i++ ){
            listequiz.add(quiz[i]);
        }
        newQuizzPageAdapter = new NewQuizzPageAdapter(NewQuizzPage.this,
                listequiz );
        quizListView.setAdapter(newQuizzPageAdapter);


        };
}
