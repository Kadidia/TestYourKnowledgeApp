package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shegd on 13/02/2017.
 */
public class NewQuizzPage extends Activity {
     ImageView plus = null;
     ListView quizListView = null;
    NewQuizzPageAdapter newQuizzPageAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newquiz);


        plus = (ImageView) findViewById(R.id.plus_button);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newQuiz = new Intent( NewQuizzPage.this, MakeQuizzPage.class );
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
        for(int i=0; i< quiz.length; i++ ){
            listequiz.add(quiz[i]);
        }
        newQuizzPageAdapter = new NewQuizzPageAdapter(NewQuizzPage.this,
                listequiz );
        quizListView.setAdapter(newQuizzPageAdapter);

        quizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent newQuizActivity = new Intent(NewQuizzPage.this, quiz_eleve.class);
                startActivity(newQuizActivity);
            }
        });

      if(getIntent().getExtras() != null)
        {
            try{

                Bundle extras = getIntent().getExtras();
             //   Log.i("afficher kkkk", extras.getString("afficher"));

                if(extras.getString("afficher").equals("ok")){
                    Log.i("contenu", "aaaa");

                 //   View v =  quizListView.getChildAt(0);

                   /* View v =  quizListView.getAdapter().getView(2, null, quizListView);
                    Log.i("contenu", "gggggg");

                    if(v == null)
                        return;*/
                    Log.i("contenu","fffff");
                  /* TextView someText = (TextView) v.findViewById(R.id.itemName);
                    ImageView image = (ImageView) v.findViewById(R.id.image);
                    someText.setText("quizz 4");
                    someText.setVisibility(View.VISIBLE);
                    image.setVisibility(View.VISIBLE);*/

                    //newQuizzPageAdapter = new NewQuizzPageAdapter(NewQuizzPage.this,
                   //         listequiz );
                    newQuizzPageAdapter.notifyDataSetChanged();
                    quizListView.setAdapter(newQuizzPageAdapter);

                    newQuizzPageAdapter.add("quiz 4");
                    newQuizzPageAdapter.notifyDataSetChanged();

                   // Log.i("contenu", someText.getText().toString());
                   // v.setBackgroundColor(Color.GREEN);

                    // someText.setText("Hi! I updated you manually!");
                    // }
                }else{
                    Log.i("contenu", "booooof");

                }

            }catch (Exception e){
                e.getMessage();
            }



        }


        }
}
