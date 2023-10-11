package com.example.team24dungeoncrawler;
import java.util.Date;

public class Attempt {
    private String playerName;
    private int score;
    private Date timestamp;

    public Attempt(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
        this.timestamp = new Date(); // Set the timestamp to the current time
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
