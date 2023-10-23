package com.example.team24dungeoncrawler.Sprint2TestUnit;
import static org.junit.Assert.*;

import com.example.team24dungeoncrawler.model.Attempt;
import com.example.team24dungeoncrawler.model.LeaderBoard;

import org.junit.Test;

import java.util.List;

public class LeaderboardTest {

    @Test
    public void testRecentAttempt() {
        // Create a Leaderboard instance
        LeaderBoard leaderboard = LeaderBoard.getInstance();

        // Create test attempts
        Attempt attempt1 = new Attempt("Player1", 100);
        Attempt attempt2 = new Attempt("Player2", 200);

        // Add attempts to the leaderboard
        leaderboard.addAttempt(attempt1);
        leaderboard.addAttempt(attempt2);

        // Check if the attempts are added successfully
        assertEquals("Player2", leaderboard.getRecentAttempt().getPlayerName());
    }
}
