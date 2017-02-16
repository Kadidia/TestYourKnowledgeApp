package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static com.example.tyk.testyourknowledge.MakeQuizzPage.pack;

/**
 * Created by shegd on 15/02/2017.
 */
public class QuizLook extends Activity {

    TextView questionEdit;
    LinearLayout looklayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizlook);

        questionEdit = (TextView) findViewById(R.id.questionLook);
        looklayout = (LinearLayout) findViewById(R.id.newLookLayout);

        Intent quizDone = getIntent();
        List<Question> quiz = quizDone.getParcelableArrayListExtra(pack);
       /*for(Question question: quiz){

            questionEdit.setText(question.getQuestion().toString());

            LayoutInflater layoutInflater =
                    (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final View lookView = layoutInflater.inflate(R.layout.rowlook, null);
        }*/

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
