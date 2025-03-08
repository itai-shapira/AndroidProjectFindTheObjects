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

        btMainActivity.setOnClickListener(new View.OnClickListener() {
            // Navigates to the Main screen
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsersActivity.this, MainActivity.class);
                startActivity(intent);
            }});

        initRecyclerView();
    }

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