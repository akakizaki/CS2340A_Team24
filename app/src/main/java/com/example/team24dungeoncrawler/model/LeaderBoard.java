package com.example.team24dungeoncrawler.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class LeaderBoard {
    private static LeaderBoard instance;
    private List<Attempt> attempts;

    private LeaderBoard() {
        attempts = new ArrayList<>();
    }

    public static LeaderBoard getInstance() {
        if (instance == null) {
            instance = new LeaderBoard();
        }
        return instance;
    }

    public List<Attempt> getTopAttempts(int numberOfAttempts) {
        // Sort the attempts by score in descending order
        Collections.sort(attempts, (a1, a2) -> Integer.compare(a2.getScore(), a1.getScore()));

        // Return the top 'numberOfAttempts' attempts
        return attempts.subList(0, Math.min(numberOfAttempts, attempts.size()));
    }

    public void addAttempt(Attempt attempt) {
        attempts.add(attempt);
    }

    public List<Attempt> getAllAttempts() {
        return attempts;
    }

    // Clear attempts when the game session is over
    public void clearAttempts() {
        attempts.clear();
    }

}
