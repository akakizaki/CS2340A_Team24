package com.example.team24dungeoncrawler.Sprint4TestUnit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.team24dungeoncrawler.model.Player;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        // Initialize a Player instance before each test
        player = Player.getInstance("TestPlayer", "Medium");
    }

    @Test
    public void testResetWithValidDifficulty() {
        // Perform some actions that modify player state
        player.setScore(100);
        player.setRow(5);
        player.setCol(3);

        // Reset the player with a valid difficulty
        player.reset("NewName", "Easy");

        // Check if the player's state is reset correctly
        assertEquals("NewName", player.getName());
        assertEquals(0, player.getScore());
        assertEquals(3, player.getRow());  // Ensure row is not changed by reset
        assertEquals(1, player.getCol());  // Ensure col is reset to the default value

        // Check if health and damageMultiplier are reset based on the new difficulty
        assertEquals(150, player.getHealth());
        assertEquals(0.8, player.getDamageMultiplier(), 0.001);
    }

    @Test
    public void testResetWithInvalidDifficulty() {
        // Set the player's state to some values
        player.setScore(50);
        player.setRow(2);
        player.setCol(4);

        // Reset the player with an invalid difficulty
        player.reset("AnotherName", "InvalidDifficulty");

        // Check if the player's state is reset correctly
        assertEquals("AnotherName", player.getName());
        assertEquals(0, player.getScore());
        assertEquals(3, player.getRow());  // Ensure row is not changed by reset
        assertEquals(1, player.getCol());  // Ensure col is reset to the default value

        // Check if health and damageMultiplier are reset to default values
        assertEquals(100, player.getHealth());
        assertEquals(1.0, player.getDamageMultiplier(), 0.001);
    }
}
