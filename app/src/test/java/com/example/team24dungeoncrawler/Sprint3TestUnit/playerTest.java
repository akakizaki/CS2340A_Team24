package com.example.team24dungeoncrawler.Sprint3TestUnit;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.example.team24dungeoncrawler.model.Player;

public class playerTest {
    private Player player;

    @Before
    public void setUp() {
        player = Player.getInstance("John", "Easy");
    }


    @Test
    public void testGetSpeed() {
        assertEquals(0, player.getSpeed());
    }



    @Test
    public void testGetDamageMultiplier() {
        assertEquals(0.8, player.getDamageMultiplier(), 0.001);
    }


    @Test
    public void testSetRowAndCol() {
        player.setRow(4);
        player.setCol(2);
        assertEquals(4, player.getRow());
        assertEquals(2, player.getCol());
    }

}