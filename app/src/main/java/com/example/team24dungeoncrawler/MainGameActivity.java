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

        //switch screen to ending screen when button is clicked
        endButton.setOnClickListener(v -> {
            Intent endgame = new Intent(this, EndingScreen.class);
            endgame.putExtra("name", name);
            startActivity(endgame);
            finish();
        });
    }
}

