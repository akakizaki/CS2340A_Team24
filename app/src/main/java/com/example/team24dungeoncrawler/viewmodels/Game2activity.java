package com.example.team24dungeoncrawler.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team24dungeoncrawler.R;
import com.example.team24dungeoncrawler.model.Player;
import com.example.team24dungeoncrawler.model.PlayerView;

public class Game2activity extends AppCompatActivity {
    RelativeLayout mainGameLayout;
    private String name;

    private int currentScore;

    private TextView scoreTextView;
    int tilemap2[][];

    private Player player;
    private PlayerView playerView;
    private double characterNumber;
    private String gameDifficulty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen_2);
        mainGameLayout = findViewById(R.id.mainGameLayout);
        tilemap2 = new int[][]{
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 2, 4, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 2, 4, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
        };
        GridLayout tilemapGrid = findViewById(R.id.tilemapGrid);
        for (int row = 0; row < tilemap2.length; row++) {
            for (int col = 0; col < tilemap2[row].length; col++) {
                int tileType = tilemap2[row][col];
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

        // Display health.
        TextView health = findViewById(R.id.health);
        if (gameDifficulty.equals("Easy")) {
            health.setText("Health: " + "100");
        } else if (gameDifficulty.equals("Medium")) {
            health.setText("Health: " + "75");
        } else if (gameDifficulty.equals("Hard")) {
            health.setText("Health: " + "50");
        }

        player = Player.getInstance(name, String.valueOf(gameDifficulty));
        playerView = new PlayerView(this); // Create a new PlayerView
        if (getIntent().hasExtra("exitPositionRow") && getIntent().hasExtra("exitPositionCol")) {
            int exitPositionRow = getIntent().getIntExtra("exitPositionRow", 0);
            int exitPositionCol = getIntent().getIntExtra("exitPositionCol", 0);
            playerView.updatePosition(exitPositionRow, exitPositionCol);
            player.setRow(exitPositionRow);
            player.setCol(exitPositionCol);
        } else {
            // Provide a default starting position if not coming from the first map.
            playerView.updatePosition(3, 1);
            player.setRow(3);
            player.setCol(1);
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

        //Get score from previous screen
        currentScore = getIntent().getIntExtra("score", 0);

        //initialize scoretextview
        scoreTextView = findViewById(R.id.scoreTextView);

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
        if (newRow >= 0 && newRow < tilemap2.length && newCol >= 0 && newCol < tilemap2[0].length) {
            int newTileType = tilemap2[newRow][newCol];

            // floor tile
            if (newTileType == 2) {
                // Update the player's position
                player.setRow(newRow);
                player.setCol(newCol);
                // Update the player view's position on the grid
                playerView.updatePosition(newRow, newCol);

                //exit tile
            } else if (newTileType == 3) {
                Intent game = new Intent(this, Game3activity.class);
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
