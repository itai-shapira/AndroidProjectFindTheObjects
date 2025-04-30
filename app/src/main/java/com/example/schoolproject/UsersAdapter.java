package com.example.schoolproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Handles the RecyclerView in the Users screen
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private List<String> UserNameList;
    private List<String> UserGamesWonList;

    // Constructor
    public UsersAdapter(List<String> UserNameList, List<String> UserGamesWonList) {
        this.UserNameList = UserNameList;
        this.UserGamesWonList = UserGamesWonList;
    }

    // Inflates Layout fir RecyclerView
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_recycler_view, parent, false);

        return new UsersViewHolder(view);
    }

    // Sets the data in the RecyclerView
    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        String userName = UserNameList.get(position);
        String userFoundObjects = UserGamesWonList.get(position);

        holder.userNameTextView.setText(userName);
        holder.userGamesWonTextView.setText(userFoundObjects);
    }

    // Returns the amount of rows in the RecyclerView
    @Override
    public int getItemCount() {
        return UserNameList.size();
    }

    // Class that handles the View parameters of the RecyclerView
    public static class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView;
        TextView userGamesWonTextView;

        // Constructor
        public UsersViewHolder(View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.tvUserName);
            userGamesWonTextView = itemView.findViewById(R.id.tvUserGamesWon);
        }
    }
}