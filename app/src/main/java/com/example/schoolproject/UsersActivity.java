package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {
    RecyclerView usersRecyclerView;
    UsersAdapter usersAdapter;
    List<String> userNameList;
    List<Integer> player1WinsList;
    List<Integer> player2WinsList;
    List<Integer> drawsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
    }

    private void initRecyclerView() {
        // Set up the RecyclerView
        usersRecyclerView = findViewById(R.id.usersRecyclerView);
        // Defines to the RecyclerView how to order the items
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Pass the list of currency rates to classs CurrencyAdapter
        usersAdapter = new UsersAdapter(userNameList, player1WinsList, player2WinsList, drawsList);
        usersRecyclerView.setAdapter(usersAdapter);
        usersAdapter.notifyDataSetChanged();
    }
}