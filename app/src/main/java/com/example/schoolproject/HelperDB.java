package com.example.schoolproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// Handles the Users table
public class HelperDB extends SQLiteOpenHelper {
    // Table information
    public static final String DB_FILE = "find_the_objects.db";
    public static final String USERS_TABLE = "Users";
    public static final String USER_NAME = "UserName";
    public static final String USER_PWD = "UserPassword";
    public static final String USER_PHONE = "UserPhone";
    public static final String USER_GAMES_WON = "UserGamesWon";
    SQLiteDatabase db;

    // Constructor
    public HelperDB(@Nullable Context context) {
        super(context, DB_FILE, null, 1);
    }

    // Creates the table in the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(buildUserTable());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Builds the Users table
    public String buildUserTable() {
        String st = "CREATE TABLE IF NOT EXISTS "+USERS_TABLE;
        st += "("+USER_NAME+" TEXT, ";
        st += USER_PWD+" TEXT, ";
        st += USER_PHONE+" TEXT, ";
        st += USER_GAMES_WON +" TEXT);";
        return st;
    }

    // Reads and returns the data from the Users table
    public ArrayList<User> getAllRecords() {
        int index;
        String name, pwd, phone, games_won;
        db = this.getReadableDatabase();
        ArrayList<User> list = new ArrayList<>();

        Cursor cursor = db.query(this.USERS_TABLE, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            index = cursor.getColumnIndex(USER_NAME);
            name = cursor.getString(index);
            index = cursor.getColumnIndex(USER_PWD);
            pwd = cursor.getString(index);
            index = cursor.getColumnIndex(USER_PHONE);
            phone = cursor.getString(index);
            index = cursor.getColumnIndex(USER_GAMES_WON);
            games_won = cursor.getString(index);
            User record = new User(name, pwd, phone, games_won);
            list.add(record);
        }

        db.close();
        return list;
    }
    // Returns a User based on input username
    public User getRecord(String userName) {
        ArrayList<User> users = this.getAllRecords();
        for (User user : users) {
            if (user.getUserName().equals(userName))
                return user;
        }
        return null;
    }

    // Updates a row in the database by username
    public void updateRow(long id, ContentValues cv) {
        db = this.getWritableDatabase();
        Cursor cursor = db.query(this.USERS_TABLE, null, null, null, null, null, null);
        // db.delete(USERS_TABLE, cursor.getColumnIndex(USER_NAME) + " = " + id, null);
        db.update(USERS_TABLE, cv, cursor.getColumnIndex(USER_NAME) + " = " + id, null);
        db.close();// close the database
    }
}