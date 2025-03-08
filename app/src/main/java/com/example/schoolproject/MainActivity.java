package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// The main screen of the app
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

        // Logs-out the user and navigates to the Intro screen when the button is pressed
        btIntroActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("username");
                editor.apply();

                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        // Navigates to the Photo screen when the button is pressed
        btPhotoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(intent);
            }
        });

        // Navigates to the users screen when the button is pressed
        btUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsersActivity.class);
                startActivity(intent);
            }
        });
    }
}