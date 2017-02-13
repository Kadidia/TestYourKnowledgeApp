package com.example.tyk.testyourknowledge;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
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

    private static final String SCHOOL_INFO_KEY = "id";
    private static final String SCHOOL_INFO_NAME = "NAME";

    private static final String PROMOTION_INFO_KEY = "id";
    private static final String PROMOTION_INFO_ID_SCHOOL = "ID_SCHOOL";

    private static final String CLASS_INFO_KEY = "id";
    private static final String CLASS_INFO_ID_PROMOTION = "ID_PROMOTION";
    private static final String CLASS_INFO_NAME = "NAME";

    private static final String MODULE_INFO_KEY = "id";
    private static final String MODULE_INFO_NAME = "NAME";

    private static final String COURSE_INFO_KEY = "id";
    private static final String COURSE_INFO_ID_USER = "ID_USER";
    private static final String COURSE_INFO_ID_MODULE = "ID_MODULE";
    private static final String COURSE_INFO_NAME = "NAME";
    private static final String COURSE_INFO_SUMMARY = "SUMMARY";

   /* private static final String COURSE_USER_INFO_KEY = "id"; */
    private static final String COURSE_USER_INFO_ID_USER = "ID_USER";
    private static final String COURSE_USER_INFO_ID_COURSE = "ID_COURSE";

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

    private static final String SCHOOL_INFO_TABLE_NAME = "SCHOOL";
    private static final String TABLE_SCHOOL_INFO_CREATE =
            "CREATE TABLE " + SCHOOL_INFO_TABLE_NAME + " (" +
            SCHOOL_INFO_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SCHOOL_INFO_NAME + TYPE_TEXT + ");";

    private static final String PROMOTION_INFO_TABLE_NAME = "PROMOTION";
    private static final String TABLE_PROMOTION_INFO_CREATE =
            "CREATE TABLE " + PROMOTION_INFO_TABLE_NAME + " (" +
            PROMOTION_INFO_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PROMOTION_INFO_ID_SCHOOL + TYPE_INTEGER + "," +
            " FOREIGN KEY (" + PROMOTION_INFO_ID_SCHOOL + ") REFERENCES " + SCHOOL_INFO_TABLE_NAME + "(" + SCHOOL_INFO_KEY + "));";

    private static final String CLASS_INFO_TABLE_NAME = "CLASS";
    private static final String TABLE_CLASS_INFO_CREATE =
            "CREATE TABLE " + CLASS_INFO_TABLE_NAME + " (" +
            CLASS_INFO_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CLASS_INFO_ID_PROMOTION + TYPE_INTEGER + "," +
            CLASS_INFO_NAME + TYPE_TEXT + "," +
            " FOREIGN KEY (" +  CLASS_INFO_ID_PROMOTION + ") REFERENCES " + PROMOTION_INFO_TABLE_NAME + "(" + PROMOTION_INFO_KEY + "));";

    private static final String MODULE_INFO_TABLE_NAME = "MODULE";
    private static final String TABLE_MODULE_INFO_CREATE =
            "CREATE TABLE " + MODULE_INFO_TABLE_NAME + " (" +
            MODULE_INFO_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MODULE_INFO_NAME + TYPE_TEXT + ");";

    private static final String COURSE_INFO_TABLE_NAME = "COURSE";
    private static final String COURSE_INFO_TABLE_CREATE =
            "CREATE TABLE " + COURSE_INFO_TABLE_NAME + " (" +
            COURSE_INFO_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COURSE_INFO_ID_USER + TYPE_INTEGER + "," +
            COURSE_INFO_ID_MODULE + TYPE_INTEGER + "," +
            COURSE_INFO_NAME + TYPE_TEXT + "," +
            COURSE_INFO_SUMMARY + TYPE_TEXT + "," +
            " FOREIGN KEY (" +  COURSE_INFO_ID_MODULE + ") REFERENCES " + MODULE_INFO_TABLE_NAME + "(" + MODULE_INFO_KEY + ")" +
            " FOREIGN KEY (" +  COURSE_INFO_ID_USER + ") REFERENCES " + USER_INFO_TABLE_NAME + "(" + USER_INFO_KEY + "));";

    private static final String COURSE_USER_INFO_TABLE_NAME = "COURSE_USER";
    private static final String COURSE_USER_INFO_TABLE_CREATE =
            "CREATE TABLE " + COURSE_USER_INFO_TABLE_NAME + " (" +
            COURSE_USER_INFO_ID_USER + TYPE_INTEGER + "," +
            COURSE_USER_INFO_ID_COURSE + TYPE_INTEGER + "," +
            " FOREIGN KEY (" +  COURSE_USER_INFO_ID_USER + ") REFERENCES " + USER_INFO_TABLE_NAME + "(" + USER_INFO_KEY + ")" +
            " FOREIGN KEY (" +  COURSE_USER_INFO_ID_COURSE + ") REFERENCES " + COURSE_INFO_TABLE_NAME + "(" + COURSE_INFO_KEY + "));";


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

    public User isUserExist(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        List<User> users = getAllUsers(db);

        for(User oneUser : users){
            if(oneUser.equals(user)) {
                db.close();
                return oneUser;
            }
        }

       /* if(users.contains(user)){
            db.close();
            return user;
        }*/
        //else{
            db.close();
            return null;
        //}
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
