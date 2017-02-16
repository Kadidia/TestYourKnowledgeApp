package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

/**
 * Created by shegd on 15/02/2017.
 */
public class QuizLook extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizlook);

        Intent quizDone = getIntent();
        //List<Question> quiz = quizDone.getExtras(MakeQuizzPage.pack);

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
