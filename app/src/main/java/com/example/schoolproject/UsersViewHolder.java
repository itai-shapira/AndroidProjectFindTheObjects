package com.example.schoolproject;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.R;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    TextView userNameTextView;
    TextView player1WinsTextView;
    TextView player2WinsTextView;
    TextView drawsTextView;

    public UsersViewHolder(View itemView) {
        super(itemView);
        userNameTextView = itemView.findViewById(R.id.tvUserName);
        player1WinsTextView = itemView.findViewById(R.id.tvPlayer1Wins);
        player2WinsTextView = itemView.findViewById(R.id.tvPlayer2Wins);
        drawsTextView = itemView.findViewById(R.id.tvDraws);
    }
}
