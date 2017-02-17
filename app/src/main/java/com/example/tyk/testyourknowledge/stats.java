package com.example.tyk.testyourknowledge;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class stats extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView toolbar_title;
    BarChart barChart;
    static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.welcomeTextView);
        toolbar_title.setText(R.string.title_activity_stats);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        barChart = (BarChart) findViewById(R.id.barChart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(14f, 0));
        barEntries.add(new BarEntry(15f, 1));
        barEntries.add(new BarEntry(14f, 2));
        barEntries.add(new BarEntry(13f, 3));
        barEntries.add(new BarEntry(17f, 4));
        BarDataSet barDataSet = new BarDataSet(barEntries, "M2");
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
        theQuiz.add(0, "E-MA");
        //theQuiz.add(1, "");
        theQuiz.add(1, "NODE");
        //theQuiz.add(3, "");
        theQuiz.add(2, "JAVA");
        //theQuiz.add(5, "");
        theQuiz.add(3, "C#");
        //theQuiz.add(7, "");
        theQuiz.add(4, "WEB");

        BarData barData = new BarData(theQuiz, barDataSet);
        barChart.setDescription("");
        barChart.setData(barData);

        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

                if (h.getXIndex() == 0){
                    Intent marketIntent = new Intent(getApplicationContext(), barChart.class);
                    startActivity(marketIntent);
                }
                if (h.getXIndex() == 1){
                    Intent nodeIntent = new Intent(getApplicationContext(), barChartNode.class);
                    startActivity(nodeIntent);
                }
                if (h.getXIndex() == 2){
                    Intent javaIntent = new Intent(getApplicationContext(), barChartJava.class);
                    startActivity(javaIntent);
                }
                if (h.getXIndex() == 3){
                    Intent CsIntent = new Intent(getApplicationContext(), barChartC.class);
                    startActivity(CsIntent);
                }
                if (h.getXIndex() == 4){
                    Intent webIntent = new Intent(getApplicationContext(), barChartWebServices.class);
                    startActivity(webIntent);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

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
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stats, menu);
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

        if (id == R.id.nav_stats) {
            // Handle the camera action
        } /*else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
