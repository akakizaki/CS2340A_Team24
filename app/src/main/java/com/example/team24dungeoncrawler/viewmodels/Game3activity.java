package com.example.team24dungeoncrawler.viewmodels;


import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
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
import com.example.team24dungeoncrawler.model.Key;
import com.example.team24dungeoncrawler.model.LeaderBoard;
import com.example.team24dungeoncrawler.model.MoveDownStrategy;
import com.example.team24dungeoncrawler.model.MoveLeftStrategy;
import com.example.team24dungeoncrawler.model.MoveRightStrategy;
import com.example.team24dungeoncrawler.model.MoveUpStrategy;
import com.example.team24dungeoncrawler.model.MovementStrategy;
import com.example.team24dungeoncrawler.model.Player;
import com.example.team24dungeoncrawler.model.PlayerView;
import com.example.team24dungeoncrawler.model.PowerUp;
import com.example.team24dungeoncrawler.model.PowerUpFactory;
import com.example.team24dungeoncrawler.model.PowerUpView;

public class Game3activity extends AppCompatActivity {
    private RelativeLayout mainGameLayout;
    private String name;

    private int currentScore;

    private TextView scoreTextView;
    private int[][] tilemap3;

    private Player player;
    private PlayerView playerView;
    private double characterNumber;
    private String gameDifficulty;
    private final Handler handler = new Handler();
    private static final int ENEMY_MOVEMENT_INTERVAL = 1000;
    private Enemy skeleton;
    private Enemy zombie;
    private EnemyView zombieView;
    private EnemyView skeletonView;
    private EnemyView skullView;
    private PowerUp healthPU;
    private PowerUp damagePU;
    private PowerUp scorePU;
    private PowerUpView healthPUView;
    private PowerUpView damagePUView;
    private PowerUpView scorePUView;
    private Key key;
    private PowerUpView keyView;
    private TextView attack;
    private GridLayout tilemapGrid;
    private boolean isGameOver = GameState.isGameOver();
    private static final int ATTACK_TEXT_DURATION = 2000;
    private long visibleStartTime;
    private SoundPool soundPool;
    private AudioManager audioManager;
    private boolean soundsLoaded;
    private int soundIDGameOver;
    private int soundIDSadTrombone;
    private int soundIDLoseHealth;
    private int playerHealthForSound;
    private int soundIDKilledEnemy;
    private float volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen_3);
        mainGameLayout = findViewById(R.id.mainGameLayout);

        visibleStartTime = System.currentTimeMillis();

        tilemap3 = new int[][]{
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
        for (int row = 0; row < tilemap3.length; row++) {
            for (int col = 0; col < tilemap3[row].length; col++) {
                int tileType = tilemap3[row][col];
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

        // Audio
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        float actVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume/maxVolume * 2;

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                soundsLoaded = true;
            }
        });
        soundIDGameOver = soundPool.load(this, R.raw.gameover, 1);
        soundIDSadTrombone = soundPool.load(this, R.raw.sadtrombone, 1);
        soundIDKilledEnemy = soundPool.load(this, R.raw.hugnergamesdead, 1);
        soundIDLoseHealth = soundPool.load(this, R.raw.r2d2screaming, 1);


        attack = findViewById(R.id.attackView3);

        // Display player Name.
        name = getIntent().getStringExtra("name");
        TextView editName = findViewById(R.id.name);
        editName.setText("Name: " + name);

        // Get difficulty selected from config screen and display it
        gameDifficulty = getIntent().getStringExtra("difficulty");
        TextView difficulty = findViewById(R.id.difficulty);
        difficulty.setText("Difficulty: " + gameDifficulty);

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

        TextView health = findViewById(R.id.health);
        health.setText("Health: " + player.getHealth());
        // Update Health every quarter second
        handler.postDelayed(healthRunnable, 250);

        skeleton = EnemyFactory.createEnemy(1, 1, 10, 1,
                1);
        skeletonView = new EnemyView(this);
        skeletonView.updatePosition(skeleton.getRow(), skeleton.getColumn());
        skeletonView.setImageResource(R.drawable.skeleton);
        player.addObserver(skeleton);
        zombie = EnemyFactory.createEnemy(4, 2, 50, 4,
                4);
        zombieView = new EnemyView(this);
        zombieView.updatePosition(zombie.getRow(), zombie.getColumn());
        zombieView.setImageResource(R.drawable.zombie);
        player.addObserver(zombie);

        healthPU = PowerUpFactory.createPowerUp(1, 17, 15);
        healthPUView = new PowerUpView(this);
        healthPUView.updatePosition(healthPU.getRow(), healthPU.getColumn());
        healthPUView.setImageResource(R.drawable.health_pu);
        player.addObserver(healthPU);

        damagePU = PowerUpFactory.createPowerUp(2, 13, 2);
        damagePUView = new PowerUpView(this);
        damagePUView.updatePosition(damagePU.getRow(), damagePU.getColumn());
        damagePUView.setImageResource(R.drawable.damage_pu);
        player.addObserver(damagePU);

        scorePU = PowerUpFactory.createPowerUp(2, 4, 7);
        scorePUView = new PowerUpView(this);
        scorePUView.updatePosition(scorePU.getRow(), scorePU.getColumn());
        scorePUView.setImageResource(R.drawable.clover_score_mult);
        player.addObserver(scorePU);

        key = new Key(18, 2);
        keyView = new PowerUpView(this);
        keyView.updatePosition(key.getRow(), key.getColumn());
        keyView.setImageResource(R.drawable.key);
        player.addObserver(key);

        handler.postDelayed(enemyMovementRunnable, ENEMY_MOVEMENT_INTERVAL);

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

        tilemapGrid.addView(playerView);
        tilemapGrid.addView(skeletonView);
        tilemapGrid.addView(zombieView);
        tilemapGrid.addView(healthPUView);
        tilemapGrid.addView(damagePUView);
        tilemapGrid.addView(scorePUView);
        tilemapGrid.addView(keyView);
        skullView = new EnemyView(this);
        skullView.setImageResource(R.drawable.skull);

        //Get score from previous screen
        currentScore = getIntent().getIntExtra("score", 0);

        //initialize scoretextview
        scoreTextView = findViewById(R.id.scoreTextView);

        // Start updating the score
        startScoreUpdate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

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
            movementStrategy.move(player, keyCode, tilemap3);
            playerView.updatePosition(player.getRow(), player.getCol());
            checkPowerUpCollisions();
            int newTileType = tilemap3[player.getRow()][player.getCol()];
            if (newTileType == 3 && player.getHasKey()) {

                //Player gets 10 - (seconds to reach the door) points after reaching door
                long timeToReachDoor = System.currentTimeMillis() - visibleStartTime;
                System.out.println(timeToReachDoor);
                int scoreChange = (10 - (int) (timeToReachDoor / 1000)) * 10;
                if (scoreChange <= 0) {
                    scoreChange = 1;
                }
                currentScore += scoreChange;


                movementStrategy = new ExitStrategy(this, gameDifficulty, name,
                        characterNumber, currentScore);
                movementStrategy.move(player, keyCode, tilemap3);
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
                playEnemyDeadSound();
                skeleton.setMovementSpeed(0);
                showAttackText();
                player.removeObserver(skeleton);
                skullView.setImageResource(R.drawable.skull);
                if (skeletonView.getParent() != null) {
                    ((ViewGroup) skeletonView.getParent()).removeView(skeletonView);
                }
                int skel = skeleton.getDel();
                skel++;
                skeleton.setDel(skel);
                addToTilemapGrid(skullView, player.getRow(), player.getCol());
            }
            if (player.getRow() == zombie.getRow() && player.getCol() == zombie.getColumn()) {
                playEnemyDeadSound();
                zombie.setMovementSpeed(0);
                showAttackText();
                player.removeObserver(zombie);
                skullView.setImageResource(R.drawable.skull);
                if (zombieView.getParent() != null) {
                    ((ViewGroup) zombieView.getParent()).removeView(zombieView);
                }
                int zom = zombie.getDel();
                zom++;
                zombie.setDel(zom);
                addToTilemapGrid(skullView, player.getRow(), player.getCol());
            }
        }
        return true;
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

                if (zombie != null) {
                    zombie.move();
                    zombieView.updatePosition(zombie.getRow(), zombie.getColumn());
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
            if (player.getHealth() < playerHealthForSound) {
                playLoseHealthSound();
                Log.d("H", "sound should have played");
            }
            TextView healthTextView = findViewById(R.id.health);
            healthTextView.setText("Health: " + player.getHealth());
            playerHealthForSound = player.getHealth();
            handler.postDelayed(this, 250);
        }
    };

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

                //currentScore -= 1; // Decrease by 1 point per second
                // Ensure the score doesn't go below 0
                if (currentScore < 0) {
                    currentScore = 0;
                }
                if(skeleton.getDel() == 1) {
                    currentScore += 10;
                    skeleton.setDel(2);
                }
                if(zombie.getDel() == 1) {
                    currentScore += 10;
                    zombie.setDel(2);
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
            playGameOverSound();
            playSadTromboneSound();
            LeaderBoard leaderboard = LeaderBoard.getInstance();
            leaderboard.addAttempt(new Attempt(name, currentScore));
            Intent gameOverIntent = new Intent(Game3activity.this, EndingScreen.class);
            gameOverIntent.putExtra("Name", name);
            gameOverIntent.putExtra("Score", currentScore);
            player.removeObservers();
            Game3activity.this.startActivity(gameOverIntent);
            Game3activity.this.finish();
        }
    }

    private void showAttackText() {
        attack.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> attack.setVisibility(View.GONE),
                ATTACK_TEXT_DURATION);
    }

    public void playGameOverSound() {
        if (soundsLoaded) {
            soundPool.play(soundIDGameOver, volume*2, volume, 1, 1, 1f);
        }
    }
    public void playSadTromboneSound() {
        if (soundsLoaded) {
            soundPool.play(soundIDSadTrombone, volume, volume*2, 1, 1, 1f);
        }
    }

    public void playEnemyDeadSound() {
        if (soundsLoaded) {
            soundPool.play(soundIDKilledEnemy, volume*10, volume*10, 1, 1, 1f);
        }
    }
    public void playLoseHealthSound() {
        if (soundsLoaded) {
            soundPool.play(soundIDLoseHealth, volume*3, volume*3, 1, 1, 1f);
        }
    }

    public void checkPowerUpCollisions() {
        if (healthPU.getRow() == player.getRow() &&  healthPU.getColumn() == player.getCol() && !healthPU.getVisibility()) {
            if (healthPUView.getParent() != null) {
                ((ViewGroup) healthPUView.getParent()).removeView(healthPUView);
            }
            healthPU.negateVisibility();
            player.removeObserver(healthPU);
        }

        if (damagePU.getRow() == player.getRow() &&  damagePU.getColumn() == player.getCol() && !damagePU.getVisibility()) {
            if (damagePUView.getParent() != null) {
                ((ViewGroup) damagePUView.getParent()).removeView(damagePUView);
            }
            damagePU.negateVisibility();
            player.removeObserver(damagePU);
        }

        if (scorePU.getRow() == player.getRow() &&  scorePU.getColumn() == player.getCol() && !scorePU.getVisibility()) {
            if (scorePUView.getParent() != null) {
                ((ViewGroup) scorePUView.getParent()).removeView(scorePUView);
            }
            scorePU.negateVisibility();
            player.removeObserver(scorePU);
        }

        if (key.getRow() == player.getRow() &&  key.getColumn() == player.getCol() && key.isVisibile()) {
            if (keyView.getParent() != null) {
                ((ViewGroup) keyView.getParent()).removeView(keyView);
            }
            key.negateVisibility();
            player.removeObserver(key);
        }
    }

}

