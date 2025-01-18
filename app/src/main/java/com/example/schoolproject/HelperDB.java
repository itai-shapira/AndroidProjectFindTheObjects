package com.example.schoolproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// Handles the Users table
public class HelperDB extends SQLiteOpenHelper {
    // Table information
    public static final String DB_FILE = "pass_game.db";
    public static final String USERS_TABLE = "Users";
    public static final String USER_NAME = "UserName";
    public static final String USER_PWD = "UserPassword";
    public static final String USER_PHONE = "UserPhone";

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
        st += USER_PHONE+" TEXT);";
        return st;
    }

    // Reads and returns the data from the Users table
    public ArrayList<User> getAllRecords(HelperDB helperDB) {
        SQLiteDatabase db;
        int index;
        String name, pwd, phone;
        db = helperDB.getReadableDatabase();
        ArrayList<User> list = new ArrayList<>();

        Cursor cursor = db.query(helperDB.USERS_TABLE, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            index = cursor.getColumnIndex(USER_NAME);
            name = cursor.getString(index);
            index = cursor.getColumnIndex(USER_PWD);
            pwd = cursor.getString(index);
            index = cursor.getColumnIndex(USER_PHONE);
            phone = cursor.getString(index);
            User record = new User(name, pwd, phone);
            list.add(record);
        }

        db.close();
        return list;
    }
}