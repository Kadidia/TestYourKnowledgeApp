package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shegd on 14/02/2017.
 */
public class MakeQuizzPage extends Activity {
    //variables du layout
    EditText question;
    EditText response;
    EditText choice;
    ImageView choice_plus;//bouton nouvelle edittext choice
    Button newquestion;// nouvelle question
    TextView questionText;


    EditText newChoiceEdit;
    LinearLayout newChoiceLayout ;
    //nouvelles variables


    int cptChoice = 1;
    private static int cptQuestion = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makequiz);

        question = (EditText) findViewById(R.id.questionEdit);
        choice = (EditText) findViewById(R.id.choice1Edit);
        response = (EditText) findViewById(R.id.reponseEdit);
        choice_plus = (ImageView) findViewById(R.id.plusChoiceBt);
        newquestion = (Button) findViewById(R.id.newQuestionBt);
        newChoiceEdit = (EditText) findViewById(R.id.newChoiceView);
        questionText = (TextView) findViewById(R.id.nextQuestionText);

        newChoiceLayout = (LinearLayout) findViewById(R.id.newChoiceLayout);

        questionText.setText("Question "+cptQuestion+" : ");

        choice_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cptChoice++;
                if(cptChoice< 5){
                  //  newChoiceEdit.setHint("choice : ");

                    LayoutInflater layoutInflater =
                            (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    final View choiceView = layoutInflater.inflate(R.layout.rowchoice, null);

                    newChoiceLayout.addView(choiceView);
                }else{
                    Toast.makeText(getApplicationContext(), "Max choice reached",Toast.LENGTH_SHORT).show();
                }





            }
        });

        newquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cptQuestion++;


                Intent sameActivity = new Intent(MakeQuizzPage.this, MakeQuizzPage.class);

                startActivity(sameActivity);
            }

        });


    }


}
