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


     List<Question> questionsList;

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

       /* if(question.getText().toString().length()>0 && response.getText().toString().length()>0
                &&choice.getText().toString().length() >0  ){

            Log.i("hello","kadi");

            Log.i("question",question.getText().toString());

            Log.i("reponse",response.getText().toString());

            Log.i("choice",choice.getText().toString());
            Log.i("choice",choice2.getText().toString());

        }else{
            Log.i("hello","booooooof");

        }*/


        if(getIntent().getExtras() != null)
        {
            Bundle extras = getIntent().getExtras();
            Log.i("Value", extras.getString("questions"));
            Log.i("Value", extras.getString("question2"));
            Log.i("Value", extras.getString("question3"));
            Log.i("Value", extras.getString("question4"));

            List<String> choicesList = new ArrayList<String>();
            choicesList.add( extras.getString("question3"));
            choicesList.add( extras.getString("question4"));

            questionsList.add(new Question(extras.getString("questions"),
                    extras.getString("question2"),choicesList));
        }





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

                    Log.i("hello","kadi");

                    Log.i("choice",choice.getText().toString());
                    Log.i("choice",choice2.getText().toString());


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
                    try{
                        Intent sameActivity = new Intent(MakeQuizzPage.this, MakeQuizzPage.class);

                       // Bundle bundle = new Bundle();
                       // sameActivity.putInt("key", 1);
                        sameActivity.putExtra("question","1");
                        sameActivity.putExtra("questions", question.getText().toString());
                        sameActivity.putExtra("question2", response.getText().toString());
                        sameActivity.putExtra("question3", choice.getText().toString());
                        sameActivity.putExtra("question4", choice2.getText().toString());
                        sameActivity.putExtra("key","1");
                        sameActivity.putExtra("key","1");

                        MakeQuizzPage.this.finish();
                        startActivity(sameActivity);
                    }catch (Exception e){
                        e.getMessage();
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"Fields Question response and choice need to be filled", Toast.LENGTH_SHORT).show();
                }

            }

        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"kadiiiiiii", Toast.LENGTH_SHORT).show();

             //   Intent quizDone = new Intent(MakeQuizzPage.this, QuizLook.class);

                Log.i("hello","kadi");

                Log.i("question",question.getText().toString());

                Log.i("reponse",response.getText().toString());

                Log.i("choice",choice.getText().toString());
                Log.i("choice",choice2.getText().toString());

                List<String> choicesList = new ArrayList<String>();
                choicesList.add(choice.getText().toString());
                choicesList.add(choice2.getText().toString());

                questionsList.add(new Question(question.getText().toString(),
                        response.getText().toString(),choicesList));

                try{
                    for(Question question : questionsList){
                        Log.i("boucle", "for");

                        Log.i("questions", question.toString());
                    }

                    Intent sameActivity = new Intent(MakeQuizzPage.this, NewQuizzPage.class);

                    // Bundle bundle = new Bundle();
                    // sameActivity.putInt("key", 1);
                    sameActivity.putExtra("afficher","ok");
                    MakeQuizzPage.this.finish();
                    startActivity(sameActivity);

                }catch (Exception e){
                    e.getMessage();
                }

               // quizDone.putExtra(MakeQuizzPage.pack, (Parcelable) questionsList);
              //  startActivity(quizDone);
                // Boite de dialog mais pas le temps
            }
        });


    }



}
