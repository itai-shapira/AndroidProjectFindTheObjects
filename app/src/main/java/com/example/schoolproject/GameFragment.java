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
import android.widget.TextView;

public class GameFragment extends Fragment {

    Button btPhotoActivity, btMainActivity;
    TextView tvTitle, tvFound;

    public GameFragment() {
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
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        btPhotoActivity = view.findViewById(R.id.btPhotoActivity);
        btMainActivity = view.findViewById(R.id.btMainActivity);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvFound = view.findViewById(R.id.tvFound);

        Context context = getActivity();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean gameInProgress = sharedPreferences.getBoolean("game_in_progress", false);

        // Check if there is a game in progress and starts one if there isn't
        if (!gameInProgress)
            startGame();

        boolean object0, object1, object2, object3;
        object0 = sharedPreferences.getBoolean("object_found0", false);
        object1 = sharedPreferences.getBoolean("object_found1", false);
        object2 = sharedPreferences.getBoolean("object_found2", false);
        object3 = sharedPreferences.getBoolean("object_found3", false);

        tvFound.setText("Object0: " + object0 + ", Object1: " + object1 + ", Object2: " + object2 + ", Object3: " + object3);

        // Checks if the win requirements have been met
        if (object0 && object1 && object2 && object3) {
            editor.putBoolean("game_in_progress", false);
            editor.apply();
            // TO-DO: ADD WIN LOGIC TO DATABASE

            // Navigates to the Win screen
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new WinFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }


        // Navigates to the Main screen when the button is pressed
        btMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        // Navigates to the Photo screen when the button is pressed
        btPhotoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new PhotoFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    // Initializes a new game
    private void startGame() {
        // Random objects to find (TO DO)
        int[] rndObjects_arr = {0, 1, 2, 3};

        Context context = getActivity();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", MODE_PRIVATE);

        // Initialize shared preferences for new game
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("game_in_progress", true);
        for (int i = 0; i < rndObjects_arr.length; i++) {
            editor.putInt("object" + i, rndObjects_arr[i]);
            editor.putBoolean("object_found" + i, false);
        }
        editor.apply();

        // Navigates to the Instructions screen
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new InstructionsFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}