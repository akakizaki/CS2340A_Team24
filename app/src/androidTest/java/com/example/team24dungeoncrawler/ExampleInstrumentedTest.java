package com.example.team24dungeoncrawler;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.team24dungeoncrawler.model.Attempt;
import com.example.team24dungeoncrawler.model.LeaderBoard;

import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private LeaderBoard leaderBoard;
    private Attempt attempt1;
    private Attempt attempt2;

    @Before
    public void setUp() {
        // Create a new leaderboard instance for each test
        leaderBoard = LeaderBoard.getInstance();
        leaderBoard.clearAttempts();

        // Create two example attempt
        attempt1 = new Attempt("Player1", 100);
        attempt2 = new Attempt("Player2", 150);
    }

    @Test
    public void testTopAttemptsDescendingOrder() {
        // Clear attempts first
        leaderBoard.clearAttempts();

        // Add some attempts with different scores (not sorted)
        Attempt attempt3 = new Attempt("Player3", 50);
        Attempt attempt4 = new Attempt("Player4", 200);

        leaderBoard.addAttempt(attempt1);
        leaderBoard.addAttempt(attempt2);
        leaderBoard.addAttempt(attempt3);
        leaderBoard.addAttempt(attempt4);

        // Top attempts
        List<Attempt> topAttempts = leaderBoard.getTopAttempts(3);

        // Check if the scores are in descending order
        for (int i = 1; i < topAttempts.size(); i++) {
            int previousScore = topAttempts.get(i - 1).getScore();
            int currentScore = topAttempts.get(i).getScore();
            assertTrue("Scores are not in descending order", previousScore >= currentScore);
        }
    }
    @Test
    public void testAddAttempt() {
        // Add an attempt to the leaderboard
        leaderBoard.addAttempt(attempt1);

        // Check if the attempt is correctly added to the leaderboard
        assertEquals(1, leaderBoard.getAllAttempts().size());
        assertTrue(leaderBoard.getAllAttempts().contains(attempt1));

        // Add another attempt and ensure it's also added
        leaderBoard.addAttempt(attempt2);
        assertEquals(2, leaderBoard.getAllAttempts().size());
        assertTrue(leaderBoard.getAllAttempts().contains(attempt2));
    }

}
