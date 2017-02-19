package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class software_quality_quiz extends AppCompatActivity implements View.OnClickListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Button soumettreButton;
    private AlphaAnimation inAnimation;
    private AlphaAnimation outAnimation;
    private boolean loadingTime = false;

    private FrameLayout progressBarHolder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_quality_quiz);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        soumettreButton = (Button) findViewById(R.id.b_soumettre);
        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder2);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_software_quality_quiz, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_soumettre :{
                Toast.makeText(software_quality_quiz.this, "Quiz transferer avec succès !", Toast.LENGTH_LONG).show();
                v.setEnabled(false);
               /// startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Db6Y82p89Rg")));
                //Log.i("Video", "Video Playing....");

                try {
                   // new Task(this).execute(true);
                 //   Intent intent = new Intent(getApplicationContext(), youTubeVideo.class);
                    // intent.putExtra("userFirstName", user.getFirstname());
                    Log.i("bouuuu", "222222");

                 //   startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }


        }break;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_software_quality_quiz, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);

            switch (position){
                case 0 : return new eMarketing_quiz_fragment();
                case 1 : return new nodeJS_quiz_fragment();
                case 2 : return new java_quiz_fragment();
                case 3 : return new csharp_quiz_fragment();
                case 4 : return new webservices_quiz_fragment();
                default:
                    Toast.makeText(software_quality_quiz.this,"Erreur !", Toast.LENGTH_SHORT);break;

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "e-MARKETING";
                case 1:
                    return "NodeJS";
                case 2:
                    return "JAVA";
                case 3:
                    return "C#";
                case 4:
                    return "Web SERVICES";
            }
            return null;
        }
    }

    private class Task extends AsyncTask<Boolean, Void, Boolean> {
        private Activity activity;

        public Task(){}
        public Task(Activity activity){ this.activity = activity;}

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //loginButton.setEnabled(false);
            inAnimation = new AlphaAnimation(0f, 1f);
            inAnimation.setDuration(200);
            progressBarHolder.setAnimation(inAnimation);
            progressBarHolder.setVisibility(View.VISIBLE);
        }

        @Override
        protected  void onPostExecute(Boolean ok){
            super.onPostExecute(ok);
            outAnimation = new AlphaAnimation(1f, 0f);
            outAnimation.setDuration(200);
            progressBarHolder.setVisibility(View.GONE);
            /*loginButton.setEnabled(true);*/

            if(ok){
                Log.i("bouuuu", "1111");

                Intent intent = new Intent(activity, youTubeVideo.class);
               // intent.putExtra("userFirstName", user.getFirstname());
                Log.i("bouuuu", "222222");

                activity.startActivity(intent);

            }else{
                Toast.makeText(software_quality_quiz.this, "bouuuuuuuu", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Boolean doInBackground(Boolean... users){

           return true;


        }
    }
}
