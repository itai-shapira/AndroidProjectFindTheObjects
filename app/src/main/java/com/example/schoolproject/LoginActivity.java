package com.example.schoolproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

// Contains all the fragments related to logging-in
public class LoginActivity extends AppCompatActivity {

    private static final int SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.fragment_container, new LoginFragment())

                    .commit();

        }
    }

    // Function to handle the results from checkPermission in RegisterFragment
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == SEND_SMS) {

            if (grantResults.length > 0

                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "SMS Permission Granted", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "SMS Permission Denied", Toast.LENGTH_SHORT).show();

            }

        }}
}