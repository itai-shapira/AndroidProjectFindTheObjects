package com.example.schoolproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// The screen where the user registers to the app
public class RegisterFragment extends Fragment {
    Button btIntroActivity, btRegister, btLoginFragment;
    EditText etUserNameRegister, etPwdRegister, etPwdCheckRegister, etPhoneRegister;
    TextView tvError;
    public RegisterFragment() {
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        btIntroActivity = view.findViewById(R.id.btIntroActivity);
        etUserNameRegister = view.findViewById(R.id.etUserNameRegister);
        etPwdRegister = view.findViewById(R.id.etPwdRegister);
        etPwdCheckRegister = view.findViewById(R.id.etPwdCheckRegister);
        etPhoneRegister = view.findViewById(R.id.etPhoneRegister);
        btRegister = view.findViewById(R.id.btRegister);
        tvError = view.findViewById(R.id.tvError);
        btLoginFragment = view.findViewById(R.id.btLoginFragment);

        Context context = getActivity();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        HelperDB helperDB = new HelperDB(getActivity());

        // Registers user, adds their info to the database and navigates to the Main screen when the button is pressed
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName, userPwd, userPwdCheck, userPhone;
                userName = etUserNameRegister.getText().toString();
                userPwd = etPwdRegister.getText().toString();
                userPwdCheck = etPwdCheckRegister.getText().toString();
                userPhone = etPhoneRegister.getText().toString();

                if (helperDB.getRecord(userName) == null) {
                    if (userPwd.equals(userPwdCheck)) {
                        User user = new User(userName, userPwd, userPhone);

                        ContentValues cv = new ContentValues();
                        cv.put(helperDB.USER_NAME, user.getUserName());
                        cv.put(helperDB.USER_PWD, user.getUserPwd());
                        cv.put(helperDB.USER_PHONE, user.getUserPhone());
                        cv.put(helperDB.USER_GAMES_WON, user.getUserGamesWon());

                        SQLiteDatabase db = helperDB.getWritableDatabase();
                        db.insert(helperDB.USERS_TABLE, null, cv);
                        db.close();

                        editor.putString("username", userName);
                        editor.apply();

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    } else
                        tvError.setText("Passwords do not match");
                } else
                    tvError.setText("Username already exists");
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

        // Navigates to the Login screen when the button is pressed
        btLoginFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new LoginFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}