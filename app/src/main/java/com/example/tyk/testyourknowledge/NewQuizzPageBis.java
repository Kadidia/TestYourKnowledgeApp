package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.tyk.testyourknowledge.MakeQuizzPage.pack;

/**
 * Created by shegd on 13/02/2017.
 */
public class NewQuizzPageBis extends Activity {
     ImageView plus = null;
     ListView quizListView = null;
    NewQuizzPageAdapter newQuizzPageAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newquiz);

        Bundle dataExtras = getIntent().getExtras();

        final List<Question> quizpack = dataExtras.getParcelableArrayList(pack);

        plus = (ImageView) findViewById(R.id.plus_button);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newQuiz = new Intent(NewQuizzPageBis.this, MakeQuizzPage.class);
                startActivity(newQuiz);
            }
        });


        quizListView = (ListView) findViewById(R.id.quizListView);

        String[] quiz = new String[]{
                "quiz 1",
                "quiz 2",
                "quiz 3"
        };


        List<String> listequiz = new ArrayList<String>();
        for (int i = 0; i < quiz.length; i++) {
            listequiz.add(quiz[i]);
        }
        listequiz.add("new quiz");
        newQuizzPageAdapter = new NewQuizzPageAdapter(NewQuizzPageBis.this,
                listequiz);
        quizListView.setAdapter(newQuizzPageAdapter);

        quizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String itemValue = (String) adapterView.getItemAtPosition(i);

                String qstring = quizpack.toString();

                switch (itemValue) {
                    case "new quiz":
                        Intent courseActivity = new Intent(NewQuizzPageBis.this, QuizLook.class);
                        courseActivity.putExtra(pack, (Parcelable) quizpack);
                        startActivity(courseActivity);
                        break;


                    default:
                        Intent newQuizActivity = new Intent(NewQuizzPageBis.this, quiz_eleve.class);
                        startActivity(newQuizActivity);
                        Toast.makeText(NewQuizzPageBis.this, "Page not found", Toast.LENGTH_SHORT);
                        break;


                }
            }

            ;


        });
    }}
