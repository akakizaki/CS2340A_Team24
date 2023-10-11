package com.example.team24dungeoncrawler.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team24dungeoncrawler.R;

public class Game3activity extends AppCompatActivity {
    RelativeLayout mainGameLayout;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game3);
        mainGameLayout = findViewById(R.id.game3);
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



        //switch screen to ending screen when button is clicked
        nextButton.setOnClickListener(v -> {
            Intent endgame = new Intent(this, EndingScreen.class);
            endgame.putExtra("name", name);
            startActivity(endgame);
            finish();
        });

    }
}

