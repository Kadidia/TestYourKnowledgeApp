package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class authentification extends AppCompatActivity implements View.OnClickListener{

    private EditText login;
    private EditText password;
    private User user;
    private UsersBDDHandler usersBDDHandler;
    private String ErrorMessage = "Sorry an error occurs...";
    private String isEmptyMessage = "Vous devez renseigner tous les champs !";
    private String failedConnectionMessage =" Echec Connexion, veuiller ré-essayer !";
    private Button loginButton;
    private AlphaAnimation inAnimation;
    private AlphaAnimation outAnimation;
    private boolean loadingTime = false;

    private FrameLayout progressBarHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        loginButton = (Button) findViewById(R.id.b_signin);
        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        usersBDDHandler = new UsersBDDHandler(this);
        user = new User("","", "", "");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_signin :{
                login = (EditText) findViewById(R.id.l_login);
                password = (EditText) findViewById(R.id.l_password);

                if(login.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(authentification.this, isEmptyMessage, Toast.LENGTH_LONG).show();
                }else {
                    user.setLogin(login.getText().toString());
                    user.setPassword(password.getText().toString());
                    new Task(this).execute(user);
                }
            }
            break;
        }
    }
    private class Task extends AsyncTask<User, Void, User> {
        private Activity activity;

        public Task(){}
        public Task(Activity activity){ this.activity = activity;}

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            loginButton.setEnabled(false);
            inAnimation = new AlphaAnimation(0f, 1f);
            inAnimation.setDuration(200);
            progressBarHolder.setAnimation(inAnimation);
            progressBarHolder.setVisibility(View.VISIBLE);
        }

        @Override
        protected  void onPostExecute(User ok){
            super.onPostExecute(ok);
            outAnimation = new AlphaAnimation(1f, 0f);
            outAnimation.setDuration(200);
            progressBarHolder.setVisibility(View.GONE);
            loginButton.setEnabled(true);

            if(ok != null){
                Intent intent = new Intent(activity, menu.class);
                Log.i("NAME", ok.getFirstname());

                intent.putExtra("userFirstName", ok.getFirstname());
                //intent.putExtra("userlastname", ok.getLastname());
                Log.i("NAME1", ok.getFirstname());

                activity.startActivity(intent);
                Log.i("NAME2", ok.getFirstname());

            }else{
                Toast.makeText(authentification.this, failedConnectionMessage, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected User doInBackground(User... users){

            User user = users[0];
            User userExist = usersBDDHandler.isUserExist(user);
            if(userExist != null) {
                return userExist;
            }
            else return null;
        }
    }
}
