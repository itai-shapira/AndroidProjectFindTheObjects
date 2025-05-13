package com.example.schoolproject;

import static android.content.Context.MODE_PRIVATE;

import static java.lang.Boolean.parseBoolean;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

// The screen containing the instructions for the game
public class InstructionsFragment extends Fragment {

    Button btGameFragment;
    CheckBox cbShowInstructions;

    public InstructionsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_instructions, container, false);

        btGameFragment = view.findViewById(R.id.btGameFragment);
        cbShowInstructions = view.findViewById(R.id.cbShowInstructions);

        HelperDB helperDB = new HelperDB(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        User currentUser = helperDB.getRecord(sharedPreferences.getString("username", "DefaultName"));

        // Sets the checkbox if the user previously checked it
        cbShowInstructions.setChecked(!parseBoolean(currentUser.getUserShowInstructions()));

        // Navigates to the Game screen when the button is pressed
        btGameFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new GameFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Sets if to show the instructions screen when the user starts a new game
        cbShowInstructions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    ContentValues cv = new ContentValues();

                    cv.put(HelperDB.USER_NAME, currentUser.getUserName());
                    cv.put(HelperDB.USER_PWD, currentUser.getUserPwd());
                    cv.put(HelperDB.USER_PHONE, currentUser.getUserPhone());
                    cv.put(HelperDB.USER_GAMES_WON, currentUser.getUserGamesWon());

                    if (isChecked) {
                        cv.put(HelperDB.USER_SHOW_INSTRUCTIONS, "" + false);
                    } else {
                        cv.put(HelperDB.USER_SHOW_INSTRUCTIONS, "" + true);
                    }

                    helperDB.updateRow(helperDB.getAllRecords().indexOf(helperDB.getRecord(currentUser.getUserName())) + 1, cv);
                }
            }
        );

        return view;
    }
}