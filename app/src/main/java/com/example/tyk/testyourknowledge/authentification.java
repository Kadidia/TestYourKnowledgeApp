package com.example.tyk.testyourknowledge;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.table.query.Query;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOperations;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.microsoft.windowsazure.mobileservices.authentication.MobileServiceAuthenticationProvider;
import com.microsoft.windowsazure.mobileservices.authentication.MobileServiceUser;


public class authentification extends AppCompatActivity implements View.OnClickListener{

    private EditText login;
    private EditText password;
    private User user;
    private UsersBDDHandlerInLocal usersBDDHandlerInLocal;
    private String ErrorMessage = "Sorry an error occurs...";
    private String isEmptyMessage = "Vous devez renseigner tous les champs !";
    private String failedConnectionMessage =" Echec Connexion, veuiller ré-essayer !";
    private Button loginButton;
    private AlphaAnimation inAnimation;
    private AlphaAnimation outAnimation;
    private boolean loadingTime = false;

    private FrameLayout progressBarHolder;

    private MobileServiceClient mClient;

   // private MobileServiceTable<UserCloud> mUserCloudTable;

    //Offline Sync
    /**
     * Mobile Service Table used to access and Sync data
     */
    private MobileServiceSyncTable<UserCloud> mUserCloudTable;
    private List<UserCloud> users;
    private UserCloud userCloud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        loginButton = (Button) findViewById(R.id.b_signin);
        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        usersBDDHandlerInLocal = new UsersBDDHandlerInLocal(this);
        user = new User("","", "", "");
        userCloud = new UserCloud("","", "", "");


        try {
            mClient = new MobileServiceClient(
                    "https://monapptyk.azurewebsites.net",
                    this
            );

            authenticate();

            // Get the Mobile Service Table instance to use

           // mUserCloudTable = mClient.getTable(UserCloud.class);

            // Offline Sync
          //  mUserCloudTable = mClient.getSyncTable("UserCloud", UserCloud.class);

            TodoItem item = new TodoItem();
            item.Text = "Awesome item";

            //Init local storage
            initLocalStore().get();


           /* List<UserCloud> liste = new ArrayList<UserCloud>();
            liste.add(new UserCloud("Kadidiatou", "MARIKO", "20170000", "Kadidiatou"));
            liste.add(new UserCloud("Corinne", "PAPAYA", "20170001", "Corinne"));
            liste.add(new UserCloud("Shegun", "MENSAH-BEAUCLAIR", "20170002", "Shegun"));
            for(UserCloud u : liste){

                mClient.getTable(UserCloud.class).insert(u, new TableOperationCallback<UserCloud>() {

                    @Override
                    public void onCompleted(UserCloud entity, Exception exception, ServiceFilterResponse response) {

                        if (exception == null) {
                            // Insert succeeded
                            Toast.makeText(getApplicationContext(), "WOOHOO User", Toast.LENGTH_LONG).show();
                        } else {
                            // Insert failed
                            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                            Log.e("error", exception.getMessage());
                            exception.printStackTrace();

                            exception.getMessage();

                        }

                    }
                });
            }*/

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (MobileServiceLocalStoreException e) {
            e.printStackTrace();
        }


    }

   /* public void showAll(final UserCloud userCloud) {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final List<UserCloud> results = mUserCloudTable.execute().get();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            for (UserCloud item : results) {
                                if(item.getLogin().equals(userCloud.getLogin()) && item.getPassword().equals(userCloud.getPassword())){

                                }
                                Toast.makeText(getApplicationContext(), item.getPassword() + " " +item.getId(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } catch (Exception exception) {
                    createAndShowDialog(exception, "Error");
                }
                return null;
            }
        };
        runAsyncTask(task);
    }*/

    /**
     * Creates a dialog and shows it
     *
     * @param exception
     *            The exception to show in the dialog
     * @param title
     *            The dialog title
     */
    private void createAndShowDialog(Exception exception, String title) {
        Throwable ex = exception;
        if(exception.getCause() != null){
            ex = exception.getCause();
        }
        createAndShowDialog(ex.getMessage(), title);
    }
    /**
     * Creates a dialog and shows it
     *
     * @param message
     *            The dialog message
     * @param title
     *            The dialog title
     */
    private void createAndShowDialog(final String message, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }








    /**
     * Run an ASync task on the corresponding executor
     * @param task
     * @return
     */
    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
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
                    userCloud.setLogin(login.getText().toString());
                    userCloud.setPassword(password.getText().toString());
                    new Task(this).execute(userCloud);
                }
            }
            break;
        }
    }
    private class Task extends AsyncTask<UserCloud, Void, Boolean> {
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
        protected  void onPostExecute(Boolean ok){
            super.onPostExecute(ok);
            outAnimation = new AlphaAnimation(1f, 0f);
            outAnimation.setDuration(200);
            progressBarHolder.setVisibility(View.GONE);
            loginButton.setEnabled(true);

            if(ok){
                Intent intent = new Intent(activity, menu.class);
                intent.putExtra("userFirstName", userCloud.getFirstname());
                activity.startActivity(intent);

            }else{
                Toast.makeText(authentification.this, failedConnectionMessage, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Boolean doInBackground(UserCloud... users){

            final UserCloud myUserCloud = users[0];
            try {
                //final List<UserCloud> results = mUserCloudTable.execute().get();
                final List<UserCloud> results = getUserFromMobileServiceTableSyncTable();

                for (UserCloud item : results) {
                    if(item.getLogin().equals(myUserCloud.getLogin()) && item.getPassword().equals(myUserCloud.getPassword())){
                        userCloud.setFirstname(item.getFirstname());
                       // Toast.makeText(getApplicationContext(), item.getPassword() + " " +item.getLogin(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                }
            } catch (Exception exception) {
                createAndShowDialog(exception, "Error");
            }

            return false;
        }
    }


    /**
     * Creates a dialog and shows it
     *
     * @param exception
     *            The exception to show in the dialog
     * @param title
     *            The dialog title
     */
    private void createAndShowDialogFromTask(final Exception exception, String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createAndShowDialog(exception, "Error");
            }
        });
    }

    /**
     * Initialize local storage
     * @return
     * @throws MobileServiceLocalStoreException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private AsyncTask<Void, Void, Void> initLocalStore() throws MobileServiceLocalStoreException, ExecutionException, InterruptedException {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    MobileServiceSyncContext syncContext = mClient.getSyncContext();

                    if (syncContext.isInitialized())
                        return null;

                    SQLiteLocalStore localStore = new SQLiteLocalStore(mClient.getContext(), "OfflineStore", null, 1);

                    Map<String, ColumnDataType> tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("firstname", ColumnDataType.String);
                    tableDefinition.put("lastname", ColumnDataType.String);
                    tableDefinition.put("login", ColumnDataType.String);
                    tableDefinition.put("password", ColumnDataType.String);

                    localStore.defineTable("UserCloud", tableDefinition);

                    SimpleSyncHandler handler = new SimpleSyncHandler();

                    syncContext.initialize(localStore, handler).get();

                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }
        };

        return runAsyncTask(task);
    }

    //Offline Sync
    /**
     * Refresh the list with the items in the Mobile Service Sync Table
     */
    private List<UserCloud> getUserFromMobileServiceTableSyncTable() throws ExecutionException, InterruptedException {
        //sync the data
        sync().get();
        Query query = QueryOperations.field("login").
                eq(val("20170000"));
        return mUserCloudTable.read(query).get();
    }

    //Offline Sync
    /**
     * Sync the current context and the Mobile Service Sync Table
     * @return
     */

    private AsyncTask<Void, Void, Void> sync() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    MobileServiceSyncContext syncContext = mClient.getSyncContext();
                    syncContext.push().get();
                    mUserCloudTable.pull(null).get();
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }
        };
        return runAsyncTask(task);
    }

    /**
    *  handle authentification with google account
    *
    * */
    private void authenticate() {
        // Login using the Google provider.

        ListenableFuture<MobileServiceUser> mLogin = mClient.login(MobileServiceAuthenticationProvider.Google);

        Futures.addCallback(mLogin, new FutureCallback<MobileServiceUser>() {
            @Override
            public void onFailure(Throwable exc) {
                createAndShowDialog((Exception) exc, "Error");
            }
            @Override
            public void onSuccess(MobileServiceUser user) {
                createAndShowDialog(String.format(
                        "You are now logged in - %1$2s",
                        user.getUserId()), "Success");
                createTable();
            }
        });
    }

    private void createTable() {

        // Get the table instance to use.
        mUserCloudTable = mClient.getSyncTable("UserCloud", UserCloud.class);

    }


}
