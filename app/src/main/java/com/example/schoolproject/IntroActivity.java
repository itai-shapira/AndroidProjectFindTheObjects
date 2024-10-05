package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {
    Button btLoginActivity, btRegisterActivity;
    CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btLoginActivity = findViewById(R.id.btLoginActivity);
        btRegisterActivity = findViewById(R.id.btRegisterActivity);

        btLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btRegisterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        cdt = new CountDownTimer(8000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btLoginActivity.setText("Login " + (millisUntilFinished / 1000 + 1) + "s");
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }.start();
    }
}