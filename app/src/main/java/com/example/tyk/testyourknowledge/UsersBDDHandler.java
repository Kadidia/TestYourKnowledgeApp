package com.example.tyk.testyourknowledge;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Axa on 29/01/2017.
 */

public class UsersBDDHandler extends SQLiteOpenHelper{
    private ArrayList<User> users;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ConnectionDB";

    private static final String USER_INFO_KEY = "id";
    private static final String USER_INFO_FIRSTNAME = "FIRSTNAME";
    private static final String USER_INFO_LASTNAME = "LASTNAME";
    private static final String USER_INFO_LOGIN = "LOGIN";
    private static final String USER_INFO_PASSWORD = "PASSWORD";

    private static final String TYPE_INTEGER = " INTEGER";
    private static final String TYPE_TEXT = " TEXT";

    private static final String USER_INFO_TABLE_NAME = "USER";
    private static final String TABLE_USER_INFO_CREATE =
            "CREATE TABLE " + USER_INFO_TABLE_NAME + " (" +
            USER_INFO_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_INFO_FIRSTNAME + TYPE_TEXT + "," +
            USER_INFO_LASTNAME + TYPE_TEXT + "," +
            USER_INFO_LOGIN + TYPE_INTEGER + "," +
            USER_INFO_PASSWORD + TYPE_TEXT + ");";

    private static final String TABLE_USER_INFO_DELETE = "DROP TABLE IF EXISTS " + USER_INFO_TABLE_NAME;

    public UsersBDDHandler( Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_INFO_CREATE);
        String count = "SELECT count(*) FROM " + USER_INFO_TABLE_NAME;

        Cursor cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);

        if(icount <= 0) initializeUsersDataBase(db);
    }

    private void initializeUsersDataBase(SQLiteDatabase db) {
        users = new ArrayList<User>();
        users.add(new User("Kadidiatou", "MARIKO", "20170000", "Kadidiatou"));
        users.add(new User("Corinne", "PAPAYA", "20170001", "Corinne"));
        users.add(new User("Shegun", "MENSAH-BEAUCLAIR", "20170002", "Shegun"));
        users.add(new User("Sophia", "GUERRASSI", "20170003", "Sophia"));
        users.add(new User("Myriam", "MOUSTATANE", "20170004", "Myriam"));
        users.add(new User("Bryan", "VIRAPA", "20170005", "Bryan"));

        for(User user : users){
            addUser(user, db);
        }
    }

    private void addUser(User user, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(USER_INFO_FIRSTNAME, user.getFirstname());
        values.put(USER_INFO_LASTNAME, user.getLastname());
        values.put(USER_INFO_LOGIN, user.getLogin());
        values.put(USER_INFO_PASSWORD, user.getPassword());

        db.insert(USER_INFO_TABLE_NAME, null, values);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_USER_INFO_DELETE);
        onCreate(db);
    }

    public boolean isUserExist(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        List<User> users = getAllUsers(db);

        if(users.contains(user)){
            db.close();
            return true;
        }
        else{
            db.close();
            return false;
        }
    }

    private List<User> getAllUsers(SQLiteDatabase db) {
        List<User> list = new ArrayList<User>();
        String selectQuery = ("SELECT * FROM ") + USER_INFO_TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                User user = new User("", "", "", "");
                user.setFirstname(cursor.getString(1));
                user.setLastname(cursor.getString(2));
                user.setLogin(cursor.getString(3));
                user.setPassword(cursor.getString(4));
                list.add(user);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
