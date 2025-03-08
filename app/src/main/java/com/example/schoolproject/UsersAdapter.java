package com.example.schoolproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private List<String> UserNameList;
    private List<String> UserFoundObjectsList;

    public UsersAdapter(List<String> UserNameList, List<String> UserFoundObjects) {
        this.UserNameList = UserNameList;
        this.UserFoundObjectsList = UserFoundObjects;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for each user
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_recycler_view, parent, false);

        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        String userName = UserNameList.get(position);
        String userFoundObjects = "Found: " + UserFoundObjectsList.get(position);

        holder.userNameTextView.setText(userName);
        holder.userFoundObjectsView.setText(userFoundObjects);
    }

    @Override
    public int getItemCount() {
        return UserNameList.size();
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView;
        TextView userFoundObjectsView;

        public UsersViewHolder(View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.tvUserName);
            userFoundObjectsView = itemView.findViewById(R.id.tvUserFoundObjects);
        }
    }
}