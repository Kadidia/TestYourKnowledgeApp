package com.example.tyk.testyourknowledge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class authentification extends AppCompatActivity {

    private Button loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        loginbutton = (Button) findViewById(R.id.b_signin);

        loginbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent in = new Intent(authentification.this, menu.class);
                startActivity(in);
            }
        });
    }
}
