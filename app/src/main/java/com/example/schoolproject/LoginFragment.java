package com.example.schoolproject;

import static android.content.Context.MODE_PRIVATE;
import static com.example.schoolproject.HelperDB.USER_NAME;
import static com.example.schoolproject.HelperDB.USER_PHONE;
import static com.example.schoolproject.HelperDB.USER_PWD;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
public class LoginFragment extends Fragment {
    Button btRecoverPasswordFragment;
    public LoginFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button btIntroActivity = view.findViewById(R.id.btIntroActivity);
        EditText etUserNameLogin = view.findViewById(R.id.etUserNameLogin);
        EditText etPwdLogin = view.findViewById(R.id.etPwdLogin);
        Button btLogin = view.findViewById(R.id.btLogin);
        Button btRegisterFragment = view.findViewById(R.id.btRegisterFragment);
        btRecoverPasswordFragment = view.findViewById(R.id.btRecoverPasswordFragment);

        Context context = getActivity();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        HelperDB helperDB = new HelperDB(getActivity());

        User user = new User("", "");

        btLogin.setOnClickListener(new View.OnClickListener() {
            // Logs-in the user (NOT DONE YET)
            @Override
            public void onClick(View v) {
                user.setUserName(etUserNameLogin.getText().toString());
                user.setUserPwd(etPwdLogin.getText().toString());

                ArrayList<User> users = helperDB.getAllRecords();

                for (User u : users) {
                    if (user.getUserName().equals(u.getUserName())) {
                        if (user.getUserPwd().equals(u.getUserPwd())) {
                            editor.putString("username", user.getUserName());
                            editor.apply();

                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        btIntroActivity.setOnClickListener(new View.OnClickListener() {
           // Navigates to the Intro screen
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroActivity.class);
                startActivity(intent);
            }
        });

        btRegisterFragment.setOnClickListener(new View.OnClickListener() {
            // Navigates to the Register screen
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new RegisterFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btRecoverPasswordFragment.setOnClickListener(new View.OnClickListener() {
            // Navigates to the Register screen
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new RecoverPasswordFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    HelperDB helperDB = new HelperDB(getActivity());
}