package com.example.team24dungeoncrawler.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team24dungeoncrawler.R;
import com.example.team24dungeoncrawler.model.Attempt;
import com.example.team24dungeoncrawler.model.LeaderBoard;
import com.example.team24dungeoncrawler.model.Player;

import java.util.List;

public class EndingScreen extends AppCompatActivity {
    private String name;
    private int score;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending_screen);

        name = getIntent().getStringExtra("Name");
        score = getIntent().getIntExtra("Score", 0);

        // Retrieve the leaderboard and display the top attempts
        LeaderBoard leaderboard = LeaderBoard.getInstance();
        Attempt recentAttempt = leaderboard.getRecentAttempt();

        // Adjust the number of attempts you want to display
        List<Attempt> topAttempts = leaderboard.getTopAttempts(5);

        RecyclerView recyclerView = findViewById(R.id.leaderboardRecyclerView);
        LeaderBoardAdapter adapter = new LeaderBoardAdapter(topAttempts);

        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        recyclerView.setAdapter(adapter);

        Button restartButton = findViewById(R.id.restartButton);

        TextView textView = findViewById(R.id.textView);
        String recentAttemptString = "Last Attempt:\n" + recentAttempt.getPlayerName() + "\n"
                + recentAttempt.getScore() + "\n"
                + recentAttempt.getTimestamp();
        textView.setText(recentAttemptString);

        // Check if the player's health is 0
        TextView textView2 = findViewById(R.id.textView2);
        if (Player.getInstance(name, "").getHealth() == 0) {
            textView.setText("GAME OVER!"); // Set the text to "Game Over"
        } else {
            textView2.setText("YOU WON");
        }

        // Set a click listener for the restart button
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate back to the MainActivity
                Intent intent = new Intent(EndingScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {
        private final List<Attempt> data;

        public LeaderBoardAdapter(List<Attempt> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent,
                    false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Attempt item = data.get(position);
            holder.nameTextView.setText(item.getPlayerName());
            holder.scoreTextView.setText(String.valueOf(item.getScore()));
            holder.datetimeTextView.setText(item.getTimestamp());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView nameTextView;
            private TextView scoreTextView;

            private TextView datetimeTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                nameTextView = itemView.findViewById(R.id.nameTextView);
                scoreTextView = itemView.findViewById(R.id.scoreTextView);
                datetimeTextView = itemView.findViewById(R.id.datetimetextView);
            }
        }
    }
}
