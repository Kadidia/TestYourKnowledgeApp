package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shegun on 12/02/2017.
 */

public class CoursePage extends Activity {

   /* ImageView plus = null;

    plus = (ImageView) findViewById(R.id.plus_button);

    plus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent newQuiz = new Intent(CoursePage.this, NewQuizzPage.class);
            startActivity(newQuiz);
        }
    });*/
    ListView courseItems =null;
    CoursePageAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);



        courseItems = (ListView) findViewById(R.id.ListCourseView);

        String[] courses = new String[]{
                "course 1",
                "course 2",
                "course 3"
        };
        List<String> listeCourse = new ArrayList<String>();
        for(int i=0; i< courses.length; i++ ){
            listeCourse.add(courses[i]);
        }
    Log.i("test", "ok1");
        courseAdapter = new CoursePageAdapter(CoursePage.this,
                listeCourse);

        Log.i("test", "ok2");

        courseItems.setAdapter(courseAdapter);
        Log.i("test", "ok3");

    }


}
