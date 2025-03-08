package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btPhotoActivity, btUsers, btIntroActivity;
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPhotoActivity = findViewById(R.id.btPhotoActivity);
        btUsers = findViewById(R.id.btUsers);
        btIntroActivity = findViewById(R.id.btIntroActivity);
        tvWelcome = findViewById(R.id.tvWelcome);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        tvWelcome.setText("Welcome " + sharedPreferences.getString("username", "DefaultName"));


        btIntroActivity.setOnClickListener(new View.OnClickListener() {
            // Logs out and navigates to the Intro screen
            @Override
            public void onClick(View v) {
                editor.remove("username");
                editor.apply();

                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        btPhotoActivity.setOnClickListener(new View.OnClickListener() {
            // Navigates to the User Details screen
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(intent);
            }
        });

        btUsers.setOnClickListener(new View.OnClickListener() {
            // Navigates to the Users screen
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsersActivity.class);
                startActivity(intent);
            }
        });
    }
}