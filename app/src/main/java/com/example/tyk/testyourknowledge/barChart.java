package com.example.tyk.testyourknowledge;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class barChart extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView toolbar_title;
    BarChart barChart;
    ArrayList<BarEntry> barEntries = new ArrayList<>();
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.welcomeTextView);
        toolbar_title.setText("E-MARKETING");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        barChart = (BarChart) findViewById(R.id.barChart);
        BarDataSet barDataSet;

                barEntries.add(new BarEntry(12f, 0));
                barEntries.add(new BarEntry(14f, 2));
                barEntries.add(new BarEntry(14f, 4));
                barEntries.add(new BarEntry(16f, 6));
                barDataSet = new BarDataSet(barEntries, "E-MARKETING");
                barDataSet.setColors(new int[]{R.color.colorApp}, getApplicationContext());

     

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setAxisMinValue(0f);
        yAxisLeft.setAxisMaxValue(20f);
        yAxisLeft.setEnabled(false);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setAxisMinValue(0f);
        yAxisRight.setAxisMaxValue(20f);
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setEnabled(false);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        ArrayList<String> theQuiz = new ArrayList<>();
        theQuiz.add(0, "Quiz1");
        theQuiz.add(1, "");
        theQuiz.add(2, "Quiz2");
        theQuiz.add(3, "");
        theQuiz.add(4, "Quiz3");
        theQuiz.add(5, "");
        theQuiz.add(6, "Quiz4");

        BarData barData = new BarData(theQuiz, barDataSet);
        barChart.setDescription("");
        barChart.setData(barData);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            i = 0;
            barEntries.clear();
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bar_chart, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
