package com.example.team24dungeoncrawler;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class EndingScreen extends AppCompatActivity {
    String name;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending_screen);

        name = getIntent().getStringExtra("name");

        // Retrieve the leaderboard and display the top attempts
        LeaderBoard leaderboard = LeaderBoard.getInstance();
        List<Attempt> topAttempts = leaderboard.getTopAttempts(5); // Adjust the number of attempts you want to display

        // Create a simple layout for leaderboard items
        RecyclerView recyclerView = findViewById(R.id.leaderboardRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }
}