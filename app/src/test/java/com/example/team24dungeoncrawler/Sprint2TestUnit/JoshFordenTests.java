package com.example.team24dungeoncrawler.Sprint2TestUnit;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.team24dungeoncrawler.model.Player;

public class JoshFordenTests {
    /**
     * Timeout var for tests. Consider altering based on your machine.
     */
    private static final int TIMEOUT = 200;

    @Test
    public void additionIsCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test(timeout = TIMEOUT)
    public void playerName() {
        Player player1 = Player.getInstance("joshua!", "Easy");
        assertEquals("joshua!", player1.getName());
    }

    @Test(timeout = TIMEOUT)
    public void playerDifficulty() {
        Player player1 = Player.getInstance("joshua!", "Easy");
        assertEquals(0.8, player1.getDamageMultiplier(), 0.1);
    }



}
