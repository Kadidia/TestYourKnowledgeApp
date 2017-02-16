package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shegd on 14/02/2017.
 */
public class MakeQuizzPage extends Activity {

    public  final  static String pack = "com.example.tyk.testyourknowledge.pack";
    //variables du layout
    EditText question;
    EditText response;
    EditText choice;
    EditText choice2;
    ImageView choice_plus;//bouton nouvelle edittext choice
    Button newquestion;// nouvelle question
    TextView questionText;
    Button submit;

    EditText newChoiceEdit;
    LinearLayout newChoiceLayout ;

    public List<Question> getQuestionsList() {
        return questionsList;
    }

     List<Question> questionsList;
    Intent quizDone = new Intent(MakeQuizzPage.this, QuizLook.class);

    //nouvelles variables


    int cptChoice = 1;
    private static int cptQuestion = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makequiz);

        question = (EditText) findViewById(R.id.questionEdit);
        choice = (EditText) findViewById(R.id.choice1Edit);
        choice2 = (EditText) findViewById(R.id.choice2Edit);
        response = (EditText) findViewById(R.id.reponseEdit);
        choice_plus = (ImageView) findViewById(R.id.plusChoiceBt);
        newquestion = (Button) findViewById(R.id.newQuestionBt);
        submit = (Button) findViewById(R.id.finishBt);
        newChoiceEdit = (EditText) findViewById(R.id.newChoiceView);
        questionText = (TextView) findViewById(R.id.nextQuestionText);

        newChoiceLayout = (LinearLayout) findViewById(R.id.newChoiceLayout);

        questionsList = new ArrayList<Question>();

        questionText.setText("Question "+cptQuestion+" : ");

        choice_plus.setOnClickListener(new View.OnClickListener() {
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
        });

        newquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cptQuestion++;

                if(question.getText().toString().length()>0 && response.getText().toString().length()>0
                        &&choice.getText().toString().length() >0  ){


                    List<String> choicesList = new ArrayList<String>();
                    choicesList.add(choice.getText().toString());
                    choicesList.add(choice2.getText().toString());



              /*  for(int i =0; i< cptChoice; i++){
                    newChoiceLayout.getChildAt(i).findViewById(R.id.newChoiceView);
                    RelativeLayout rl = (RelativeLayout) findViewById(R.id.rowchoiceLayout);
                   Log.i("Choices", ( rl.getChildCount()));

                   newChoiceEdit = (EditText) newChoiceLayout.getChildAt(i);
                   Log.i("Choices",newChoiceLayout.getChildAt(i).toString() );


                   choicesList.add(newChoiceEdit.getText().toString());
                }*/
                    questionsList.add(new Question(question.getText().toString(),
                            response.getText().toString(),choicesList));
                    //Log.i("question", newChoiceEdit.getText().toString());
                    Intent sameActivity = new Intent(MakeQuizzPage.this, MakeQuizzPage.class);

                    startActivity(sameActivity);
                }else {
                    Toast.makeText(getApplicationContext(),"Fields Question response and choice need to be filled", Toast.LENGTH_SHORT).show();
                }

            }

        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizDone.putExtra(MakeQuizzPage.pack, (Parcelable) questionsList);
                startActivity(quizDone);
                // Boite de dialog mais pas le temps
            }
        });


    }
    public List<Question> getQuiz(){
        return questionsList;
    }


}
