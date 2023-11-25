package com.example.team24dungeoncrawler.Sprint5TestUnit;

import static org.junit.Assert.assertEquals;

import com.example.team24dungeoncrawler.model.Player;

import org.junit.Before;
import org.junit.Test;
public class JoshFordenTest {

    private Player player;
    @Before
    public void setUp() {
        // Initialize a Player instance before each test
        player = Player.getInstance("TestPlayer", "Medium");
    }
}
