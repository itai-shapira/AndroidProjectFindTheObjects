package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

// The screen where all the user's statistics can be viewed
public class UsersActivity extends AppCompatActivity {

    Button btMainActivity;
    RecyclerView usersRecyclerView;
    UsersAdapter usersAdapter;
    ArrayList<String> userNameList;
    ArrayList<String> userGamesWon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        btMainActivity = findViewById(R.id.btMainActivity);

        // Navigates to the Main screen when the button is pressed
        btMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsersActivity.this, MainActivity.class);
                startActivity(intent);
            }});

        initRecyclerView();
    }

    // creates the RecyclerView where all the users' stats are displayed
    @SuppressLint("NotifyDataSetChanged")
    private void initRecyclerView() {
        userNameList = new ArrayList<String>();
        userGamesWon = new ArrayList<String>();

        HelperDB helperDB = new HelperDB(UsersActivity.this);
        ArrayList<User> users = helperDB.getAllRecords();
        for (User user : users) {
            userNameList.add(user.getUserName());
            userGamesWon.add(user.getUserGamesWon());
        }

        // Set up the RecyclerView
        usersRecyclerView = findViewById(R.id.usersRecyclerView);
        // Defines to the RecyclerView how to order the items
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Pass the list of Users to class UsersAdapter
        usersAdapter = new UsersAdapter(userNameList, userGamesWon);
        usersRecyclerView.setAdapter(usersAdapter);
        usersAdapter.notifyDataSetChanged();
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
            Intent intent = new Intent(UsersActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.miGameFragment) {
            // Navigates to the Game screen
            Intent intent = new Intent(UsersActivity.this, GameActivity.class);
            startActivity(intent);
        }
        if (id == R.id.miIntroActivity) {
            // Logs-out the user and navigates to the Intro screen
            SharedPreferences sharedPreferences = UsersActivity.this.getSharedPreferences("MyPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.remove("username");
            editor.putBoolean("game_in_progress", false);
            editor.apply();

            Intent intent = new Intent(UsersActivity.this, IntroActivity.class);
            startActivity(intent);
        }
        return true;
    }
}