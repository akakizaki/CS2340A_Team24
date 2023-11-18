package com.example.team24dungeoncrawler.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.team24dungeoncrawler.R;
import com.example.team24dungeoncrawler.model.Attempt;
import com.example.team24dungeoncrawler.model.Enemy;
import com.example.team24dungeoncrawler.model.EnemyFactory;
import com.example.team24dungeoncrawler.model.ExitStrategy;
import com.example.team24dungeoncrawler.model.LeaderBoard;
import com.example.team24dungeoncrawler.model.MoveDownStrategy;
import com.example.team24dungeoncrawler.model.MoveLeftStrategy;
import com.example.team24dungeoncrawler.model.MoveRightStrategy;
import com.example.team24dungeoncrawler.model.MoveUpStrategy;
import com.example.team24dungeoncrawler.model.MovementStrategy;
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
    private MovementStrategy movementStrategy;
    private int[][] tilemap;
    private final Handler handler = new Handler();
    private static final int ENEMY_MOVEMENT_INTERVAL = 1000;

    private Enemy vampire;
    private Enemy skeleton;
    private EnemyView ghostView;
    private EnemyView zombieView;

    private EnemyView skeletonView;
    private EnemyView vampireView;
    private EnemyView skullView;

    private Handler scoreHandler = new Handler();

    private TextView attack;

    private GridLayout tilemapGrid;

    private boolean isGameOver = GameState.isGameOver();

    private static final int ATTACK_TEXT_DURATION = 2000;

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
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
        };
        tilemapGrid = findViewById(R.id.tilemapGrid);
        int row;
        for (row = 0; row < tilemap.length; row++) {
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

        attack = findViewById(R.id.attackView);

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
        if (player.getCol() != 1 && player.getRow() != 3) {
            player.setRow(3);
            player.setCol(1);
        }
        playerView.updatePosition(player.getRow(), player.getCol()); // Set the initial position
        skeleton = EnemyFactory.createEnemy(1, 1, 10, 1,
                1); // multiplied damage by 10 to have more noticeable affect on health
        skeletonView = new EnemyView(this);
        skeletonView.updatePosition(skeleton.getRow(), skeleton.getColumn());
        skeletonView.setImageResource(R.drawable.skeleton);
        player.addObserver(skeleton);
        vampire = EnemyFactory.createEnemy(2, 2, 20, 6,
                1); // multiplied damage by 10 to have more noticeable affect on health
        vampireView = new EnemyView(this);
        vampireView.updatePosition(vampire.getRow(), vampire.getColumn());
        vampireView.setImageResource(R.drawable.vampire);
        player.addObserver(vampire);


        handler.postDelayed(enemyMovementRunnable, ENEMY_MOVEMENT_INTERVAL);


        // Display health.
        TextView health = findViewById(R.id.health);
        health.setText("Health: " + player.getHealth());
        // Update Health every quarter second
        handler.postDelayed(healthRunnable, 250);

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
        //add player to tilemap
        tilemapGrid.addView(playerView);
        tilemapGrid.addView(skeletonView);
        tilemapGrid.addView(vampireView);
        skullView = new EnemyView(this);
        skullView.setImageResource(R.drawable.skull);


        // Start updating the score
        startScoreUpdate();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        scoreHandler.removeCallbacksAndMessages(null);
    }

    private Runnable enemyMovementRunnable = new Runnable() {

        @Override
        public void run() {
            synchronized (this) {
                if (skeleton != null) {
                    skeleton.move();
                    skeletonView.updatePosition(skeleton.getRow(), skeleton.getColumn());
                } else {
                    Log.d("movement", "null");
                }

                if (vampire != null) {
                    vampire.move();
                    vampireView.updatePosition(vampire.getRow(), vampire.getColumn());
                } else {
                    Log.d("movement", "null");
                }
            }

            // Schedule the next movement
            handler.postDelayed(this, ENEMY_MOVEMENT_INTERVAL);
        }
    };

    private Runnable healthRunnable = new Runnable() {
        @Override
        public void run() {
            TextView healthTextView = findViewById(R.id.health);
            healthTextView.setText("Health: " + player.getHealth());
            handler.postDelayed(this, 250);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MovementStrategy movementStrategy;
        switch (keyCode) {
        case KeyEvent.KEYCODE_W:
            movementStrategy = new MoveUpStrategy();
            break;
        case KeyEvent.KEYCODE_A:
            movementStrategy = new MoveLeftStrategy();
            break;
        case KeyEvent.KEYCODE_S:
            movementStrategy = new MoveDownStrategy();
            break;
        case KeyEvent.KEYCODE_D:
            movementStrategy = new MoveRightStrategy();
            break;
        default:
            movementStrategy = null;
        }

        if (movementStrategy != null) {
            movementStrategy.move(player, keyCode, tilemap);
            playerView.updatePosition(player.getRow(), player.getCol());
            int newTileType = tilemap[player.getRow()][player.getCol()];
            if (newTileType == 3) {
                movementStrategy = new ExitStrategy(this, gameDifficulty, name,
                        characterNumber, currentScore);
                movementStrategy.move(player, keyCode, tilemap);
            }
        }
        return true;
    }
    private void addToTilemapGrid(View view, int row, int column) {
        if (view.getParent() instanceof ViewGroup) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        tilemapGrid.addView(view);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.rowSpec = GridLayout.spec(row);
        layoutParams.columnSpec = GridLayout.spec(column);
        view.setLayoutParams(layoutParams);
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            if (player.getRow() == skeleton.getRow() && player.getCol() == skeleton.getColumn()) {
                skeleton.setMovementSpeed(0);
                showAttackText();
                player.removeObserver(skeleton);
                skullView.setImageResource(R.drawable.skull);

                if (skeletonView.getParent() != null) {
                    ((ViewGroup) skeletonView.getParent()).removeView(skeletonView);
                }
                addToTilemapGrid(skullView, player.getRow(), player.getCol());
                Log.d("skull", "done");
            }
            if (player.getRow() == vampire.getRow() && player.getCol() == vampire.getColumn()) {
                vampire.setMovementSpeed(0);
                showAttackText();
                vampireView.setImageResource(R.drawable.skull);
                if (vampireView.getParent() != null) {
                    ((ViewGroup) vampireView.getParent()).removeView(vampireView);
                }
                addToTilemapGrid(skullView, player.getRow(), player.getCol());
                Log.d("skullVampire", "done");
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
                // Check if player health is zero or below
                if (player.getHealth() <= 0) {
                    // Player has died, navigate to the game over screen
                    gameOver();
                    return; // Stop further updates
                }

                currentScore -= 1; // Decrease by 1 point per second
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

    private void gameOver() {
        if (!GameState.isGameOver()) {
            GameState.setGameOver(true);
            LeaderBoard leaderboard = LeaderBoard.getInstance();
            leaderboard.addAttempt(new Attempt(name, currentScore));
            Intent gameOverIntent = new Intent(MainGameActivity.this, EndingScreen.class);
            gameOverIntent.putExtra("Name", name);
            gameOverIntent.putExtra("Score", currentScore);
            player.removeObservers();
            MainGameActivity.this.startActivity(gameOverIntent);
            MainGameActivity.this.finish();
        }
    }

    private void showAttackText() {
        attack.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> attack.setVisibility(View.GONE),
                ATTACK_TEXT_DURATION);
    }
}
