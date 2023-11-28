package com.example.team24dungeoncrawler.Sprint5TestUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.team24dungeoncrawler.model.Player;
import com.example.team24dungeoncrawler.model.Ghost;
import com.example.team24dungeoncrawler.model.Vampire;
import com.example.team24dungeoncrawler.model.Zombie;

import org.junit.Test;

public class RajPatelTest {
    @Test
    public void testVampireInteractionWithPlayer() {
        // Create a player and a vampire
        Player player = new Player("TestPlayer", "Easy");
        Vampire vampire = new Vampire(1, 10, 5, 5);
        assertEquals(5, vampire.getRow());
        assertEquals(5, vampire.getColumn());
        vampire.update(player);
        assertNotEquals(100, player.getHealth());
    }
    @Test
    public void testVampireMovementSpeedChange() {
        Vampire vampire = new Vampire(1, 10, 5, 5);
        vampire.setMovementSpeed(2);
        vampire.move();
        assertEquals(5, vampire.getRow());
    }

    @Test
    public void testGhostInteractionWithPlayer() {
        // Create a player and a ghost
        Player player = new Player("TestPlayer", "Easy");
        Ghost ghost = new Ghost(1, 10, 5, 5);
        assertEquals(5, ghost.getRow());
        assertEquals(5, ghost.getColumn());
        ghost.update(player);
        assertNotEquals(100, player.getHealth());
    }
    @Test
    public void testGhostMovementSpeedChange() {
        Ghost ghost = new Ghost(1, 10, 5, 5);
        ghost.setMovementSpeed(2);
        ghost.move();
        assertEquals(5, ghost.getRow());
    }

    @Test
    public void testZombieInteractionWithPlayer() {
        // Create a player and a zombie
        Player player = new Player("TestPlayer", "Easy");
        Zombie zombie = new Zombie(1, 10, 5, 5);
        assertEquals(5, zombie.getRow());
        assertEquals(5, zombie.getColumn());
        zombie.update(player);
        assertNotEquals(100, player.getHealth());
    }
    @Test
    public void testZombieMovementSpeedChange() {
        Zombie zombie = new Zombie(1, 10, 5, 5);
        zombie.setMovementSpeed(2);
        zombie.move();
        assertEquals(5, zombie.getRow());
    }

}
