package com.example.tyk.testyourknowledge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;

public class menu extends menu_principal
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView items;
    private homePageAdapter homePageAdapter;
    TextView toolbar_title;
    TextView userFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.welcomeTextView);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

       // userfirstname = (TextView) findViewById(R.id.user_firstname);
        Log.i("NAME", "1");

       /* Bundle dataExtras = getIntent().getExtras();
        Log.i("NAME", "2");
       // Log.i("NAME", dataExtras.getString("userfirstname"));
        String firstName = dataExtras.getString("userFirstName");
        Log.i("first", firstName);
        //userfirstname.setText(firstName);
        //Log.i("NAME", dataExtras.getString("userfirstname"));

        //Log.i("NAME 5", userfirstname.toString()); */

        Bundle dataExtras = getIntent().getExtras();
        String firstName = dataExtras.getString("userFirstName");



        List<String> items_list = new ArrayList<String>();
        items_list.add("Mes cours");
        items_list.add("Mes Quizz");
        items_list.add("Messagerie");
        items_list.add("Mes notes");
        items_list.add("Statistiques Quiz");
        items_list.add("Courses Tutorials");
        items = (ListView) findViewById(R.id.list_item);

        Log.i("datas", "t1");
        homePageAdapter = new homePageAdapter(menu.this, items_list);
        Log.i("datas", "t2");

        items.setAdapter(homePageAdapter);
        Log.i("datas", "t3");

        items.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) parent.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),
                        "Position :"+position+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

                switch (itemValue){
                    case "Mes cours":
                        Intent courseActivity = new Intent(menu.this, CoursePage.class);
                        startActivity(courseActivity);break;
                    case "Mes Quizz":
                        Intent intent = new Intent(getApplicationContext(), modules_page.class);
                        startActivity(intent);break;
                    case "Courses Tutorials":
                        Intent coursesTutorials = new Intent(getApplicationContext(), youTubeVideo.class);
                        startActivity(coursesTutorials);break;

                    default:
                        Toast.makeText(menu.this,"Page not found", Toast.LENGTH_SHORT);break;

                }
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        userFirstName = (TextView) header.findViewById(R.id.user_firstName);
        userFirstName.setText(firstName);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        ViewFlipper vf = (ViewFlipper) findViewById(R.id.vf);

       if (id == R.id.nav_profil) {
           //Intent intent = new Intent(getApplicationContext(), profil.class);
           //startActivity(intent);
           vf.setDisplayedChild(1);
            // Handle the camera action
        } else if (id == R.id.nav_exit) {
           Intent intent = new Intent(getApplicationContext(), authentification.class);
           startActivity(intent);
        } /*else if (id == R.id.nav_videos) {
           vf.setDisplayedChild(2);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
