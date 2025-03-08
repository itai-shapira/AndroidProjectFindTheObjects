package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

// Contains all the fragments related to logging-in
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.fragment_container, new LoginFragment())

                    .commit();

        }
    }
}