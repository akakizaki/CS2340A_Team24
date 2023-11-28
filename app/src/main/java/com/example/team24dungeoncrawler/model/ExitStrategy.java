package com.example.team24dungeoncrawler.model;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team24dungeoncrawler.viewmodels.EndingScreen;
import com.example.team24dungeoncrawler.viewmodels.Game2activity;
import com.example.team24dungeoncrawler.viewmodels.Game3activity;
import com.example.team24dungeoncrawler.viewmodels.MainGameActivity;

public class ExitStrategy implements MovementStrategy {
    private AppCompatActivity activity;
    private String gameDifficulty;
    private String name;
    private double characterNumber;
    private int currentScore;

    public ExitStrategy(AppCompatActivity activity, String gameDifficulty,
                        String name, double characterNumber, int currentScore) {
        this.activity = activity;
        this.gameDifficulty = gameDifficulty;
        this.name = name;
        this.characterNumber = characterNumber;
        this.currentScore = currentScore;
    }

    @Override
    public void move(Player player, int keyCode, int[][] tilemap) {
        // Handle exit tile logic
        if (activity instanceof MainGameActivity) {
            Intent game = new Intent(activity, Game2activity.class);
            game.putExtra("difficulty", gameDifficulty);
            game.putExtra("name", name);
            game.putExtra("characterNumber", characterNumber);
            game.putExtra("score", currentScore);
            player.removeObservers();
            player.setHasKey(false);
            activity.startActivity(game);
            activity.finish();
        } else if (activity instanceof Game2activity) {
            Intent game = new Intent(activity, Game3activity.class);
            game.putExtra("difficulty", gameDifficulty);
            game.putExtra("name", name);
            game.putExtra("characterNumber", characterNumber);
            game.putExtra("score", currentScore);
            player.removeObservers();
            player.setHasKey(false);
            activity.startActivity(game);
            activity.finish();
        } else if (activity instanceof Game3activity) {
            LeaderBoard leaderboard = LeaderBoard.getInstance();
            leaderboard.addAttempt(new Attempt(name, currentScore));
            Intent endgame = new Intent(activity, EndingScreen.class);
            endgame.putExtra("Name", name);
            endgame.putExtra("Score", currentScore);
            player.removeObservers();
            player.setHasKey(false);
            activity.startActivity(endgame);
            activity.finish();
        }
    }
}
