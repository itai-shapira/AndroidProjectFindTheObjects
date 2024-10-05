package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    Button btIntroActivity, btLogin, btRegisterActivity;
    EditText etUserNameLogin, etPwdLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btIntroActivity = findViewById(R.id.btIntroActivity);
        etUserNameLogin = findViewById(R.id.etUserNameLogin);
        etPwdLogin = findViewById(R.id.etPwdLogin);
        btLogin = findViewById(R.id.btLogin);
        btRegisterActivity = findViewById(R.id.btRegisterActivity);

        User user = new User("", "");
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUserName(etUserNameLogin.getText().toString());
                user.setUserPwd(etPwdLogin.getText().toString());
            }
        });

        btIntroActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });

        btRegisterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}