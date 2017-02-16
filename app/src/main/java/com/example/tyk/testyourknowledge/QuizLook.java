package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import static com.example.tyk.testyourknowledge.MakeQuizzPage.pack;

/**
 * Created by shegd on 15/02/2017.
 */
public class QuizLook extends Activity {

    TextView questionEdit;
    TextView responseEdit;
    LinearLayout looklayout;
    ListView responseListView =null;
    QuizLookAdapter quizLookAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizlook);

        questionEdit = (TextView) findViewById(R.id.questionLook);
        responseEdit = (TextView) findViewById(R.id.responseLook);
        looklayout = (LinearLayout) findViewById(R.id.newLookLayout);
        responseListView = (ListView) findViewById(R.id.ListQuestionView);

        //Intent quizDone = getIntent();
        Bundle dataExtras = getIntent().getExtras();

        List<Question> quiz = dataExtras.getParcelableArrayList(pack);
        Log.i("look", "on arrive ici");
        Log.i("look", quiz.get(0).getQuestion().toString());

      for(Question question: quiz){

           Log.i("look", question.getQuestion().toString());

           // questionEdit.setText(question.getQuestion().toString());

           quizLookAdapter = new QuizLookAdapter(QuizLook.this, question.getChoices());

           responseListView.setAdapter(quizLookAdapter);

           /* LayoutInflater layoutInflater =
                    (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final View lookView = layoutInflater.inflate(R.layout.rowlook, null);
           looklayout.addView(lookView);*/
        }

        /* choice_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cptChoice++;
                if(cptChoice< 4){
                  //  newChoiceEdit.setHint("choice : ");

                    LayoutInflater layoutInflater =
                            (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    final View choiceView = layoutInflater.inflate(R.layout.rowchoice, null);

                    newChoiceLayout.addView(choiceView);
                }else{
                    Toast.makeText(getApplicationContext(), "Max choice reached",Toast.LENGTH_SHORT).show();
                }





            }
        });*/

    }

}
