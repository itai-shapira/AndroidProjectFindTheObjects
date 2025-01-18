package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btUserDetails;
    Button btUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btUserDetails = findViewById(R.id.btUserDetails);
        btUsers = findViewById(R.id.btUsers);

        btUserDetails.setOnClickListener(new View.OnClickListener() {
            // Navigates to the User Details screen
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserDetailsActivity.class);
                startActivity(intent);
            }
        });

        btUserDetails.setOnClickListener(new View.OnClickListener() {
            // Navigates to the Users screen
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsersActivity.class);
                startActivity(intent);
            }
        });
    }
}