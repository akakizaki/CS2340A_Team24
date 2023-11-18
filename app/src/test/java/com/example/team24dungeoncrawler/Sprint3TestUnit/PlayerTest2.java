package com.example.team24dungeoncrawler.Sprint3TestUnit;

import static org.junit.Assert.assertEquals;

import com.example.team24dungeoncrawler.model.Player;

import org.junit.Test;

public class PlayerTest2 {

    /**
     * Tests that the default damageMultiplier is 1 if difficulty entered is not correct.
     */
    @Test
    public void testDefaultDamageMultiplier() {
        Player player2 = Player.getInstance("John", "notACorrectDifficulty");
        assertEquals(1, player2.getDamageMultiplier(), 0.001);
    }
}
