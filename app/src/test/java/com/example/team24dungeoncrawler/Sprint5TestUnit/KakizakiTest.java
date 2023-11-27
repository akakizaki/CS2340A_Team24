package com.example.team24dungeoncrawler.Sprint5TestUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.team24dungeoncrawler.model.Player;
import com.example.team24dungeoncrawler.model.Skeleton;


import org.junit.Before;
import org.junit.Test;

public class KakizakiTest {
    @Test
    public void testSkeletonInteractionWithPlayer() {
        // Create a player and a skeleton
        Player player = new Player("TestPlayer", "Easy");
        Skeleton skeleton = new Skeleton(1, 10, 5, 5);

        assertEquals(5, skeleton.getRow());
        assertEquals(5, skeleton.getColumn());
        skeleton.update(player);

        assertNotEquals(100, player.getHealth());
    }
    @Test
    public void testSkeletonMovementSpeedChange() {
        Skeleton skeleton = new Skeleton(1, 10, 5, 5);
        skeleton.setMovementSpeed(2);
        skeleton.move();

        assertEquals(5, skeleton.getRow());
    }

}
