package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.fragment_container, new GameFragment())

                    .commit();

        }
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
        if (id == R.id.miMainActivity) {
            // Navigates to the Main screen
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.miUsersActivity) {
            // Navigates to the Users screen
            Intent intent = new Intent(GameActivity.this, UsersActivity.class);
            startActivity(intent);
        }
        if (id == R.id.miIntroActivity) {
            // Logs-out the user and navigates to the Intro screen
            SharedPreferences sharedPreferences = GameActivity.this.getSharedPreferences("MyPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.remove("username");
            editor.putBoolean("game_in_progress", false);
            editor.apply();

            Intent intent = new Intent(GameActivity.this, IntroActivity.class);
            startActivity(intent);
        }
        return true;
    }
}