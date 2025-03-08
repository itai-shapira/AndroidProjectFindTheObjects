package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

// The screen where all the user's statistics can be viewed
public class UsersActivity extends AppCompatActivity {

    Button btMainActivity;
    RecyclerView usersRecyclerView;
    UsersAdapter usersAdapter;
    ArrayList<String> userNameList;
    ArrayList<String> userFoundObjectsList;

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

    // creates the RecyclerView where all the user's stats are displayed
    private void initRecyclerView() {
        userNameList = new ArrayList<String>();
        userFoundObjectsList = new ArrayList<String>();

        HelperDB helperDB = new HelperDB(UsersActivity.this);
        ArrayList<User> users = helperDB.getAllRecords();
        for (User user : users) {
            userNameList.add(user.getUserName());
            userFoundObjectsList.add(user.getFoundObjectsString());
        }

        // Set up the RecyclerView
        usersRecyclerView = findViewById(R.id.usersRecyclerView);
        // Defines to the RecyclerView how to order the items
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Pass the list of Users to class UsersAdapter
        usersAdapter = new UsersAdapter(userNameList, userFoundObjectsList);
        usersRecyclerView.setAdapter(usersAdapter);
        usersAdapter.notifyDataSetChanged();
    }
}