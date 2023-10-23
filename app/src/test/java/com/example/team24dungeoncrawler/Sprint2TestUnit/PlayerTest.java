package com.example.team24dungeoncrawler.Sprint2TestUnit;
import static org.junit.Assert.*;
import com.example.team24dungeoncrawler.model.Player;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    private Player player;

    @Before
    public void playerConstructor() {
        // Create a new Player instance before each test
        player = Player.getInstance("TestPlayer", "Medium");
    }

    @Test
    public void testPlayerGetName() {
        // Verify that the getName method returns the expected name
        assertEquals("TestPlayer", player.getName());
    }

    @Test
    public void testPlayerSetScore() {
        // Set a new score and verify that it's updated correctly
        player.setScore(1500);
        assertEquals(1500, player.getScore());
    }

    @Test
    public void testPlayerGetScore() {
        // Verify that the getScore method returns the initial score (0)
        assertEquals(1500, player.getScore());
    }

}
