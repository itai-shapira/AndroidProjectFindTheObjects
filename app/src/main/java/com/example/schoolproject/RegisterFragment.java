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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button btIntroActivity = view.findViewById(R.id.btIntroActivity);
        EditText etUserNameRegister = view.findViewById(R.id.etUserNameRegister);
        EditText etPwdRegister = view.findViewById(R.id.etPwdRegister);
        EditText etPwdCheckRegister = view.findViewById(R.id.etPwdCheckRegister);
        EditText etPhoneRegister = view.findViewById(R.id.etPhoneRegister);
        Button btRegister = view.findViewById(R.id.btRegister);
        Button btLoginFragment = view.findViewById(R.id.btLoginFragment);

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
                cv.put(helperDB.USER_FOUND_OBJECTS,"000000");

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