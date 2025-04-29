package com.example.schoolproject;

import static android.content.Context.MODE_PRIVATE;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameFragment extends Fragment {

    Button btPhotoActivity, btMainActivity, btRestart;
    TextView tvTitle, tvFound;
    CheckBox cbObject0, cbObject1, cbObject2, cbObject3;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        final String[] CLASSES = {"Backpack", "Battery", "Calculator", "Charger", "Glasses", "Notebook", "Pencil", "Plastic Bottle", "Shoe", "Umbrella"};

        btPhotoActivity = view.findViewById(R.id.btPhotoActivity);
        btMainActivity = view.findViewById(R.id.btMainActivity);
        btRestart = view.findViewById(R.id.btRestart);
        tvTitle = view.findViewById(R.id.tvTitle);
        cbObject0 = view.findViewById(R.id.cbObject0);
        cbObject1 = view.findViewById(R.id.cbObject1);
        cbObject2 = view.findViewById(R.id.cbObject2);
        cbObject3 = view.findViewById(R.id.cbObject3);

        HelperDB helperDB = new HelperDB(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        User currentUser = helperDB.getRecord(sharedPreferences.getString("username", "DefaultName"));
        boolean gameInProgress = sharedPreferences.getBoolean("game_in_progress", false);

        // Check if there is a game in progress and starts one if there isn't
        if (!gameInProgress)
            startGame();

        // Get Shared Preferences Values
        int[] objects = new int[4];
        boolean[] objectsFound = new boolean[4];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = sharedPreferences.getInt("object" + i, 0);
            objectsFound[i] = sharedPreferences.getBoolean("object_found" + i, false);
        }

        // Set Found Objects
        cbObject0.setText(CLASSES[objects[0]]);
        cbObject0.setChecked(objectsFound[0]);
        cbObject1.setText(CLASSES[objects[1]]);
        cbObject1.setChecked(objectsFound[1]);
        cbObject2.setText(CLASSES[objects[2]]);
        cbObject2.setChecked(objectsFound[2]);
        cbObject3.setText(CLASSES[objects[3]]);
        cbObject3.setChecked(objectsFound[3]);

        // Checks if the win requirements have been met
        if (objectsFound[0] && objectsFound[1] && objectsFound[2] && objectsFound[3]) {
            editor.putBoolean("game_in_progress", false);
            editor.apply();

            // Updates the Database
            int userGamesWon = parseInt(currentUser.getUserGamesWon()) + 1;
            ContentValues cv = new ContentValues();

            cv.put(helperDB.USER_NAME, currentUser.getUserName());
            cv.put(helperDB.USER_PWD, currentUser.getUserPwd());
            cv.put(helperDB.USER_PHONE, currentUser.getUserPhone());
            cv.put(helperDB.USER_GAMES_WON, userGamesWon);
            helperDB.updateRow(helperDB.getAllRecords().indexOf(helperDB.getRecord(currentUser.getUserName())) + 1, cv);

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
        // Starts a new game
        btRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
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
        // Random objects to find
        List<Integer> rndObjects = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(rndObjects);

        Context context = getActivity();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", MODE_PRIVATE);

        // Initialize shared preferences for new game
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("game_in_progress", true);
        for (int i = 0; i < 4; i++) {
            editor.putInt("object" + i, rndObjects.get(i));
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