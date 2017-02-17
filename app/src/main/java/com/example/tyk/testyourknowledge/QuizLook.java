package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import static com.example.tyk.testyourknowledge.MakeQuizzPage.pack;

/**
 * Created by shegd on 15/02/2017.
 */
public class QuizLook extends Activity {


    ListView responseListView =null;
    QuizLookAdapter quizLookAdapter;
    Button publish;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizlook);

       publish = (Button)findViewById(R.id.publishBtn);
        responseListView = (ListView) findViewById(R.id.ListQuestionView);

        //Intent quizDone = getIntent();
        Bundle dataExtras = getIntent().getExtras();

        final List<Question> quiz = dataExtras.getParcelableArrayList(pack);
        Log.i("look", "on arrive ici la taille  est "+quiz.size());

        quizLookAdapter = new QuizLookAdapter(this, quiz);

        responseListView.setAdapter(quizLookAdapter);


        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizLook.this,NewQuizzPageBis.class);
                //intent.putExtra(pack, quiz.toString());
                intent.putExtra(pack, (Serializable) quiz);

                startActivityForResult(intent, 0);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == 0) {
            // On vérifie aussi que l'opération s'est bien déroulée
            if (resultCode == RESULT_OK) {
                // On affiche le bouton qui a été choisi
                Toast.makeText(this, "Vous avez choisi le bouton " , Toast.LENGTH_SHORT).show();
                Bundle datas = data.getExtras();
                List<Question> quiz = datas.getParcelableArrayList("newQcmfile");


            }
        }
    }

}
