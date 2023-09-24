package com.example.team24dungeoncrawler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainGameActivity extends AppCompatActivity {
    RelativeLayout mainGameLayout;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game_activity);
        mainGameLayout = findViewById(R.id.mainGameLayout);
        Button endButton = findViewById(R.id.exitButton);


        // Display player Name.
        name = getIntent().getStringExtra("name");
        TextView editName = findViewById(R.id.name);
        editName.setText("Name: " + name);


        // Get difficulty selected from config screen and display it
        double gameDifficulty = getIntent().getDoubleExtra("difficulty", 1);
        TextView difficulty = findViewById(R.id.difficulty);
        if (gameDifficulty == 1.0) {
            difficulty.setText("Difficulty: " + "Easy");
        } else if (gameDifficulty == 1.5) {
            difficulty.setText("Difficulty: " + "Medium");
        } else if (gameDifficulty == 2.0) {
            difficulty.setText("Difficulty: " + "Hard");
        }


        // Display health.
        TextView health = findViewById(R.id.health);
        if (gameDifficulty == 1.0) {
            health.setText("Health: " + "100");
        } else if (gameDifficulty == 1.5) {
            health.setText("Health: " + "75");
        } else if (gameDifficulty == 2.0) {
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

        //switch screen to ending screen when button is clicked
        endButton.setOnClickListener(v -> {
            Intent endgame = new Intent(this, EndingScreen.class);
            endgame.putExtra("name", name);
            startActivity(endgame);
            finish();
        });
    }
}

