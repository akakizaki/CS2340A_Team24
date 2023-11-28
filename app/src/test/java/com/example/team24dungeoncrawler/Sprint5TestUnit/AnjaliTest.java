package com.example.team24dungeoncrawler.Sprint5TestUnit;

import static org.junit.Assert.assertEquals;

import com.example.team24dungeoncrawler.model.HealthPowerUp;
import com.example.team24dungeoncrawler.model.Player;
import com.example.team24dungeoncrawler.model.Player;
import com.example.team24dungeoncrawler.model.ScorePowerUp;

import org.junit.Before;
import org.junit.Test;

public class AnjaliSprint5Test {
    private Player player;

    @Before
    public void setUp() {
        player = new Player("ava","Easy"); // Assuming BasicPlayer implements PlayerInterface
    }

    @Test
    public void testHealthPowerUp() {
        assertEquals(150, player.getHealth());

        // Apply HealthPowerUp
        HealthPowerUp healthPowerUp = new HealthPowerUp((Player) player);
        player = healthPowerUp;
        assertEquals(175, player.getHealth());
    }

    @Test
    public void testScorePowerUp() {
        assertEquals(0, player.getScore());

        // Apply HealthPowerUp
        ScorePowerUp scorePowerUp = new ScorePowerUp(player);
        player = scorePowerUp;
        assertEquals(10, player.getScore());
    }
}
