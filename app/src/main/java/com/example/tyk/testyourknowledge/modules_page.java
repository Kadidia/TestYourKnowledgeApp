package com.example.tyk.testyourknowledge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class modules_page extends AppCompatActivity {
    private UsersBDDHandler db;
    private ListView modules;
    private ModulePageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */
        db = new UsersBDDHandler(this);
        modules = (ListView) findViewById(R.id.modules_list);

        try{
            List<Module> modules_list = db.getAllModules();

            adapter = new ModulePageAdapter(modules_page.this, modules_list);
            modules.setAdapter(adapter);

            modules.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick( AdapterView<?> parent, View view, int position, long id) {
                    // String itemValue = (String) parent.getItemAtPosition(position);
                    Module itemValue = (Module) parent.getItemAtPosition(position);

                    switch (itemValue.getName()){
                        case "Fundamentals":
                            Intent courseActivity = new Intent(modules_page.this, quiz_eleve.class);
                            startActivity(courseActivity);break;
                        case "Software Quality":
                            Intent intent = new Intent(getApplicationContext(), software_quality_quiz.class);
                            startActivity(intent);break;

                        default:
                            Toast.makeText(modules_page.this,"Page not found", Toast.LENGTH_SHORT);break;

                    }
                }
            });

        }catch (Exception e){
            Log.i("exception", e.getMessage());
        }

    }

}
