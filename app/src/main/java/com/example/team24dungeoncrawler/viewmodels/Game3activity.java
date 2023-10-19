package com.example.team24dungeoncrawler.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team24dungeoncrawler.R;
import com.example.team24dungeoncrawler.model.Attempt;
import com.example.team24dungeoncrawler.model.LeaderBoard;

public class Game3activity extends AppCompatActivity {
    RelativeLayout mainGameLayout;
    private String name;

    private int currentScore;

    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen_3);
        mainGameLayout = findViewById(R.id.mainGameLayout);
        Button nextButton = findViewById(R.id.exitButton);

        // Display player Name.
        name = getIntent().getStringExtra("name");
        TextView editName = findViewById(R.id.name);
        editName.setText("Name: " + name);

        // Get difficulty selected from config screen and display it
        String gameDifficulty = getIntent().getStringExtra("difficulty");
        TextView difficulty = findViewById(R.id.difficulty);
        difficulty.setText("Difficulty: " + gameDifficulty);

        // Display health.
        TextView health = findViewById(R.id.health);
        if (gameDifficulty.equals("Easy")) {
            health.setText("Health: " + "100");
        } else if (gameDifficulty.equals("Medium")) {
            health.setText("Health: " + "75");
        } else if (gameDifficulty.equals("Hard")) {
            health.setText("Health: " + "50");
        }

        // Get characterNumber and display sprite accordingly
        double characterNumber = getIntent().getDoubleExtra("characterNumber", 1);
        ImageView characterImage = findViewById(R.id.characterImage);
        if (characterNumber == 1) {
            characterImage.setImageResource(R.drawable.sprite1);
        } else if (characterNumber == 2) {
            characterImage.setImageResource(R.drawable.sprite2);
        } else if (characterNumber == 3) {
            characterImage.setImageResource(R.drawable.sprite3);
        }

        //Get score from previous screen
        currentScore = getIntent().getIntExtra("score", 0);

        //initialize scoretextview
        scoreTextView = findViewById(R.id.scoreTextView);

        // Start updating the score
        startScoreUpdate();

        //switch screen to ending screen when button is clicked
        nextButton.setOnClickListener(v -> {
            LeaderBoard leaderboard = LeaderBoard.getInstance();
            leaderboard.addAttempt(new Attempt(name, currentScore));
            Intent endgame = new Intent(this, EndingScreen.class);
            endgame.putExtra("Name", name);
            endgame.putExtra("Score", currentScore);
            startActivity(endgame);
            finish();
        });

    }

    private void startScoreUpdate() {
        Handler scoreHandler = new Handler();
        Runnable scoreRunnable = new Runnable() {
            @Override
            public void run() {
                currentScore -= 1; // Decrease by 1 points per second
                // Ensure the score doesn't go below 0
                if (currentScore < 0) {
                    currentScore = 0;
                }

                // Update the score display
                scoreTextView.setText("Score " + currentScore);

                // Repeat the score update every second
                scoreHandler.postDelayed(this, 1000);
            }
        };

        // Start the score update
        scoreHandler.postDelayed(scoreRunnable, 1000);
    }
}

