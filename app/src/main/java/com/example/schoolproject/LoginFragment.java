package com.example.schoolproject;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// The screen where the user logs-in
public class LoginFragment extends Fragment {
    Button btIntroActivity, btLogin, btRegisterFragment, btRecoverPasswordFragment;
    EditText etUserNameLogin, etPwdLogin;
    TextView tvError;
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

        btIntroActivity = view.findViewById(R.id.btIntroActivity);
        etUserNameLogin = view.findViewById(R.id.etUserNameLogin);
        etPwdLogin = view.findViewById(R.id.etPwdLogin);
        btLogin = view.findViewById(R.id.btLogin);
        tvError = view.findViewById(R.id.tvError);
        btRegisterFragment = view.findViewById(R.id.btRegisterFragment);
        btRecoverPasswordFragment = view.findViewById(R.id.btRecoverPasswordFragment);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        HelperDB helperDB = new HelperDB(getActivity());

        // Logs-in the user and navigates to the Login screen when the button is pressed
        btLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String userName = etUserNameLogin.getText().toString();
                String userPwd = etPwdLogin.getText().toString();

                User user = helperDB.getRecord(userName);

                if (user != null) {
                    if (userPwd.equals(user.getUserPwd())) {
                        editor.putString("username", userName);
                        editor.apply();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    } else
                        tvError.setText("Incorrect Password");
                } else
                    tvError.setText("User does not exist");
            }
        });

        // Navigates to the Intro screen when the button is pressed
        btIntroActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroActivity.class);
                startActivity(intent);
            }
        });

        // Navigates to the Register screen when the button is pressed
        btRegisterFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new RegisterFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Navigates to the Recover Password screen when the button is pressed
        btRecoverPasswordFragment.setOnClickListener(new View.OnClickListener() {
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
}