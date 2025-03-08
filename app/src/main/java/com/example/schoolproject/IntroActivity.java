package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

// The first screen that opens when you launch the app
public class IntroActivity extends AppCompatActivity {
    Button btLoginFragment;
    SharedPreferences sharedPreferences;
    String currentUser;
    final String DEFAULT_NAME = "DefaultName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btLoginFragment = findViewById(R.id.btLoginFragment);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        currentUser = sharedPreferences.getString("username", DEFAULT_NAME);

        // Navigates to the Main screen if the previous user didn't log-out
        if (!currentUser.equals(DEFAULT_NAME)) {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
        }


        // Navigates to the Login screen when the button is pressed
        btLoginFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}