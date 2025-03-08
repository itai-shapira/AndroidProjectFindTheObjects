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
    private List<String> UserFoundObjectsList;

    // Constructor
    public UsersAdapter(List<String> UserNameList, List<String> UserFoundObjects) {
        this.UserNameList = UserNameList;
        this.UserFoundObjectsList = UserFoundObjects;
    }

    // Inflate the item layout for each user
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
        String userFoundObjects = "Found: " + UserFoundObjectsList.get(position);

        holder.userNameTextView.setText(userName);
        holder.userFoundObjectsView.setText(userFoundObjects);
    }

    // Returns the amount of rows in the RecyclerView
    @Override
    public int getItemCount() {
        return UserNameList.size();
    }

    // Class that handles connecting to the RecycerView xml file
    public static class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView;
        TextView userFoundObjectsView;

        // Constructor
        public UsersViewHolder(View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.tvUserName);
            userFoundObjectsView = itemView.findViewById(R.id.tvUserFoundObjects);
        }
    }
}