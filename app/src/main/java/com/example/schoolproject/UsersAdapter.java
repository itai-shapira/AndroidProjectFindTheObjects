package com.example.schoolproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.R;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    private List<String> UserNameList;
    private List<Integer> Player1WinsList;
    private List<Integer> Player2WinsList;
    private List<Integer> DrawsList;

    public UsersAdapter(List<String> UserNameList, List<Integer> Player1WinsList, List<Integer> Player2WinsList, List<Integer> DrawsList) {
        this.UserNameList = UserNameList;
        this.Player1WinsList = Player1WinsList;
        this.Player2WinsList = Player2WinsList;
        this.DrawsList = DrawsList;
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
        int player1Wins = Player1WinsList.get(position);
        int player2Wins = Player2WinsList.get(position);
        int draws = DrawsList.get(position);

        holder.userNameTextView.setText(userName);
        holder.player1WinsTextView.setText(player1Wins);
        holder.player2WinsTextView.setText(player2Wins);
        holder.drawsTextView.setText(draws);
    }

    @Override
    public int getItemCount() {
        return UserNameList.size();
    }
}