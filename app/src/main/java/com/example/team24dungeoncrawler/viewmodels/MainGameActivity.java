package com.example.team24dungeoncrawler.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.team24dungeoncrawler.R;
import com.example.team24dungeoncrawler.model.Player;
import com.example.team24dungeoncrawler.model.PlayerView;

public class MainGameActivity extends AppCompatActivity {
    private RelativeLayout mainGameLayout;
    private String name;
    private Player player;
    private TextView scoreTextView;
    private PlayerView playerView;
    private int currentScore = 30;

    private String gameDifficulty;
    private double characterNumber;


    int tilemap[][];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game_activity);
        mainGameLayout = findViewById(R.id.mainGameLayout);

        tilemap = new int[][]{
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
        };
        GridLayout tilemapGrid = findViewById(R.id.tilemapGrid);
        for (int row = 0; row < tilemap.length; row++) {
            for (int col = 0; col < tilemap[row].length; col++) {
                int tileType = tilemap[row][col];
                ImageView tileView = new ImageView(this);
                // Set the background resource based on tileType
                if (tileType == 0) {
                    tileView.setBackgroundResource(R.drawable.left_wall_tile);
                } else if (tileType == 1) {
                    tileView.setBackgroundResource(R.drawable.top_wall_tile);
                } else if (tileType == 2) {
                    tileView.setBackgroundResource(R.drawable.floor_tile);
                } else if (tileType == 3) {
                    tileView.setBackgroundResource(R.drawable.exit_tile);
                } else if (tileType == 4) {
                    tileView.setBackgroundResource(R.drawable.right_wall_tile);
                } else if (tileType == 5) {
                    tileView.setBackgroundResource(R.drawable.bottom_wall_tile);
                }
                tilemapGrid.addView(tileView);
            }

        }

        // Display player Name.
        name = getIntent().getStringExtra("name");
        TextView editName = findViewById(R.id.name);
        editName.setText("Name: " + name);

        // Get difficulty selected from config screen and display it
        gameDifficulty = getIntent().getStringExtra("difficulty");
        TextView difficulty = findViewById(R.id.difficulty);
        difficulty.setText("Difficulty: " + gameDifficulty);

        player = Player.getInstance(name, String.valueOf(difficulty));
        playerView = new PlayerView(this); // Create a new PlayerView
        playerView.updatePosition(player.getRow(), player.getCol()); // Set the initial position


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
        characterNumber = getIntent().getDoubleExtra("characterNumber", 1);
        //ImageView characterImage = findViewById(R.id.characterImage);
        if (characterNumber == 1) {
            playerView.setImageResource(R.drawable.sprite1);
        } else if (characterNumber == 2) {
            playerView.setImageResource(R.drawable.sprite2);
        } else if (characterNumber == 3) {
            playerView.setImageResource(R.drawable.sprite3);
        }
        //initialize scoretextview
        scoreTextView = findViewById(R.id.scoreTextView);


        tilemapGrid.addView(playerView);


        // Start updating the score
        startScoreUpdate();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int newRow = player.getRow();
        int newCol = player.getCol();

        switch (keyCode) {
            case KeyEvent.KEYCODE_W:
                newRow -= 1;
                break;
            case KeyEvent.KEYCODE_A:
                newCol -= 1;
                break;
            case KeyEvent.KEYCODE_S:
                newRow += 1;
                break;
            case KeyEvent.KEYCODE_D:
                newCol += 1;
                break;
        }

        // Check if the new position is within the bounds of the tilemap
        if (newRow >= 0 && newRow < tilemap.length && newCol >= 0 && newCol < tilemap[0].length) {
            int newTileType = tilemap[newRow][newCol];

            // floor tile
            if (newTileType == 2) {
                // Update the player's position
                player.setRow(newRow);
                player.setCol(newCol);
                // Update the player view's position on the grid
                playerView.updatePosition(newRow, newCol);

                //exit tile
            } else if (newTileType == 3) {
                Intent game = new Intent(this, Game2activity.class);
                game.putExtra("difficulty", gameDifficulty);
                game.putExtra("name", name);
                game.putExtra("characterNumber", characterNumber);
                game.putExtra("score", currentScore);
                startActivity(game);
                finish();
            }
        }
        return true;
    }


    //other methods
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
