package com.example.schoolproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class RegisterFragment extends Fragment {
    Button btIntroActivity, btRegister, btLoginFragment;
    EditText etUserNameRegister, etPwdRegister, etPwdCheckRegister, etPhoneRegister;
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
        btLoginFragment = view.findViewById(R.id.btLoginFragment);

        HelperDB helperDB = new HelperDB(getActivity());

        User user = new User("", "", "");

        btRegister.setOnClickListener(new View.OnClickListener() {
            // Registers user, adds their info to the database and navigates to the Main screen
            @Override
            public void onClick(View v) {
                user.setUserName(etUserNameRegister.getText().toString());
                user.setUserPwd(etPwdRegister.getText().toString());
                user.setUserPhone(etPhoneRegister.getText().toString());

                ContentValues cv = new ContentValues();
                cv.put(helperDB.USER_NAME,user.getUserName());
                cv.put(helperDB.USER_PWD,user.getUserPwd());
                cv.put(helperDB.USER_PHONE,user.getUserPhone());
                cv.put(helperDB.USER_FOUND_OBJECTS,user.getUserFoundObjects());

                SQLiteDatabase db = helperDB.getWritableDatabase();
                db.insert(helperDB.USERS_TABLE, null, cv);
                db.close();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
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

        btLoginFragment.setOnClickListener(new View.OnClickListener() {
            // Navigates to the Login screen
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