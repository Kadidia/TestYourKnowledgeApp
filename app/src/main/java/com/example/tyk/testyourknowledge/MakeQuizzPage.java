package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shegd on 14/02/2017.
 */
public class MakeQuizzPage extends Activity {

    public  final  static String pack = "com.example.tyk.testyourknowledge.pack";
    private static final int CHOOSE_BUTTON_REQUEST = 0;
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


     List<Question> questionsList = new ArrayList<Question>();

    //nouvelles variables


    int cptChoice = 1;
    private static int cptQuestion = 1;


    protected void onCreate(final Bundle savedInstanceState) {
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



        questionText.setText("Question "+cptQuestion+" : ");

        //questionsList = new ArrayList<Question>();

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

                   //Log.i("look", choice.getText().toString());


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
                   Log.i("look", "la taille est de "+questionsList.size() );
                    Intent sameActivity = new Intent(MakeQuizzPage.this, MakeQuizzPage.class);

                   /* if(getIntent().toString() != "newQuiz"){
                        questionsList =getIntent().getExtras().getParcelableArrayList(pack);
                    }*/


                   sameActivity.putExtra(MakeQuizzPage.pack, (Serializable) questionsList);
                    setResult(RESULT_OK, sameActivity);
                    finish();
                    startActivityForResult(sameActivity,CHOOSE_BUTTON_REQUEST);
                }else {
                    Toast.makeText(getApplicationContext(),"Fields Question response and choice need to be filled", Toast.LENGTH_SHORT).show();
                }

            }

        });

       // Log.i("look", String.valueOf(questionsList.get(0)));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> choicesList = new ArrayList<String>();
                choicesList.add(choice.getText().toString());
                choicesList.add(choice2.getText().toString());
                questionsList.add(new Question(question.getText().toString(),
                        response.getText().toString(),choicesList));
                Log.i("look", questionsList.get(0).getQuestion().toString() );

                Intent quizDone = new Intent(MakeQuizzPage.this, QuizLook.class);
                quizDone.putExtra(MakeQuizzPage.pack, (Serializable) questionsList);
                startActivity(quizDone);
                // Boite de dialog mais pas le temps
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == CHOOSE_BUTTON_REQUEST) {
            // On vérifie aussi que l'opération s'est bien déroulée
            if (resultCode == RESULT_OK) {
                // On affiche le bouton qui a été choisi
                Toast.makeText(this, "Vous avez choisi le bouton " , Toast.LENGTH_SHORT).show();
           Bundle datas = data.getExtras();
                questionsList = datas.getParcelableArrayList(pack);
            }
        }
    }

}
