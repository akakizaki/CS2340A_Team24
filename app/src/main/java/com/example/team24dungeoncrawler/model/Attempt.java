package com.example.team24dungeoncrawler.model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Attempt {
    private String playerName;
    private int score;
    private String timestamp;

    public Attempt(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
        SimpleDateFormat sdf = new SimpleDateFormat("'Date\n'dd-MM-yyyy '\nTime\n'HH:mm:ss z");
        String currentDateAndTime = sdf.format(new Date());
        this.timestamp = currentDateAndTime;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
