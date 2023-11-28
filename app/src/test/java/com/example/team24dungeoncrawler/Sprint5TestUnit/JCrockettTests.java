package com.example.team24dungeoncrawler.Sprint5TestUnit;


import static org.junit.Assert.assertEquals;

import com.example.team24dungeoncrawler.model.PowerUp;
import com.example.team24dungeoncrawler.model.EnemyFactory;
import com.example.team24dungeoncrawler.model.Enemy;
import com.example.team24dungeoncrawler.model.Player;
import com.example.team24dungeoncrawler.model.PowerUpFactory;

import org.junit.Before;
import org.junit.Test;

/** @noinspection deprecation*/
public class JCrockettTests {
    private Player player;

    private PowerUp healthPU;
    private PowerUp scorePU;
    private PowerUp damagePU;
    private Enemy vampire;
    private int[][] tilemap;

    @Before
    public void setUp() {
        player = Player.getInstance("John", "Easy");
        healthPU = PowerUpFactory.createPowerUp(1, 2, 17);
        damagePU = PowerUpFactory.createPowerUp(2, 18, 7);
        scorePU = PowerUpFactory.createPowerUp(3, 16, 8);
        player.addObserver(healthPU);
        player.addObserver(damagePU);
        player.addObserver(scorePU);
        tilemap = new int[][]{
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
                {0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
        };
    }

    @Test
    public void testHealthPowerUpCollisions() {
        assertEquals(150, player.getHealth());
        player.setRow(2);
        player.setCol(17);
        assertEquals(175, player.getHealth());
    }

    @Test
    public void testDamagePowerUpCollisions() {
        assertEquals(0.8, player.getDamageMultiplier(), 0.01);
        player.setRow(18);
        player.setCol(7);
        assertEquals(0.6, player.getDamageMultiplier(), 0.01);
    }

    @Test
    public void testScorePowerUpCollisions() {
        assertEquals(0, player.getScore());
        player.setRow(16);
        player.setCol(8);
        assertEquals(10,player.getScore());
    }
}
