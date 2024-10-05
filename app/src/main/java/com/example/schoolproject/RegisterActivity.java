package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    Button btIntroActivity, btRegister, btLoginActivity;
    EditText etUserNameRegister, etPwdRegister, etPwdCheckRegister, etLastNameRegister, etPhoneRegister, etEmailRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btIntroActivity = findViewById(R.id.btIntroActivity);
        etUserNameRegister = findViewById(R.id.etUserNameRegister);
        etPwdRegister = findViewById(R.id.etPwdRegister);
        etPwdCheckRegister = findViewById(R.id.etPwdCheckRegister);
        etLastNameRegister = findViewById(R.id.etLastNameRegister);
        etPhoneRegister = findViewById(R.id.etPhoneRegister);
        etEmailRegister = findViewById(R.id.etEmailRegister);
        btRegister = findViewById(R.id.btRegister);
        btLoginActivity = findViewById(R.id.btLoginActivity);

        User user = new User("", "", "", "", "");
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUserName(etUserNameRegister.getText().toString());
                user.setUserPwd(etPwdRegister.getText().toString());
                user.setUserLastName(etLastNameRegister.getText().toString());
                user.setUserPhone(etPhoneRegister.getText().toString());
                user.setUserEmail(etEmailRegister.getText().toString());
            }
        });

        btIntroActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });

        btLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}