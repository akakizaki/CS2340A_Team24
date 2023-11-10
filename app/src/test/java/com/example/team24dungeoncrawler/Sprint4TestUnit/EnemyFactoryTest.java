package com.example.team24dungeoncrawler.Sprint4TestUnit;

import com.example.team24dungeoncrawler.model.Enemy;
import com.example.team24dungeoncrawler.model.EnemyFactory;
import com.example.team24dungeoncrawler.model.Ghost;
import com.example.team24dungeoncrawler.model.Skeleton;
import com.example.team24dungeoncrawler.model.Vampire;
import com.example.team24dungeoncrawler.model.Zombie;

import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyFactoryTest {

    @Test
    public void createSkeleton() {
        // Test creating a skeleton
        Enemy skeleton = EnemyFactory.createEnemy(1, 1,
                1, 1, 1);
        assertNotNull(skeleton);
        assertTrue(skeleton instanceof Skeleton);
        assertEquals(1, skeleton.getRow());
        assertEquals(1, skeleton.getColumn());
    }

    @Test
    public void createVampire() {
        // Test creating a vampire
        Enemy vampire = EnemyFactory.createEnemy(2, 2,
                2, 6, 1);
        assertNotNull(vampire);
        assertTrue(vampire instanceof Vampire);
        assertEquals(6, vampire.getRow());
        assertEquals(1, vampire.getColumn());
    }

    @Test
    public void createGhost() {
        // Test creating a ghost
        Enemy ghost = EnemyFactory.createEnemy(3, 2,
                2, 6, 1);
        assertNotNull(ghost);
        assertTrue(ghost instanceof Ghost);
        assertEquals(6, ghost.getRow());
        assertEquals(1, ghost.getColumn());
    }

    @Test
    public void createZombie() {
        // Test creating a zombie
        Enemy zombie = EnemyFactory.createEnemy(4, 2,
                2, 6, 1);
        assertNotNull(zombie);
        assertTrue(zombie instanceof Zombie);
        assertEquals(6, zombie.getRow());
        assertEquals(1, zombie.getColumn());
    }

}
