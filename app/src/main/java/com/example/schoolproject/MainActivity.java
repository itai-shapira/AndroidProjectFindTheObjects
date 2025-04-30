package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// The main screen of the app
public class MainActivity extends AppCompatActivity {

    Button btGameFragment, btUsers, btIntroActivity;
    TextView tvWelcome;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGameFragment = findViewById(R.id.btGameFragment);
        btUsers = findViewById(R.id.btUsers);
        btIntroActivity = findViewById(R.id.btIntroActivity);
        tvWelcome = findViewById(R.id.tvWelcome);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String currentUserName = sharedPreferences.getString("username", "DefaultName");

        tvWelcome.setText("Welcome " + currentUserName);

        // Logs-out the user and navigates to the Intro screen when the button is pressed
        btIntroActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("username");
                editor.putBoolean("game_in_progress", false);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        // Navigates to the Game screen when the button is pressed
        btGameFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        // Navigates to the Users screen when the button is pressed
        btUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsersActivity.class);
                startActivity(intent);
            }
        });
    }
    // Create Menu
    @Override
    public  boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.miGameFragment) {
            // Navigates to the Game screen
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        }
        if (id == R.id.miUsersActivity) {
            // Navigates to the Users screen
            Intent intent = new Intent(MainActivity.this, UsersActivity.class);
            startActivity(intent);
        }
        if (id == R.id.miIntroActivity) {
            // Logs-out the user and navigates to the Intro screen
            SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.remove("username");
            editor.putBoolean("game_in_progress", false);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(intent);
        }
        return true;
    }
}