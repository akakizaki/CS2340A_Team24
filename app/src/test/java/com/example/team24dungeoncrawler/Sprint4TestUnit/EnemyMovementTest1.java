package com.example.team24dungeoncrawler.Sprint4TestUnit;



import static org.junit.Assert.assertEquals;

import com.example.team24dungeoncrawler.model.Ghost;
import com.example.team24dungeoncrawler.model.Skeleton;
import com.example.team24dungeoncrawler.model.Vampire;
import com.example.team24dungeoncrawler.model.Zombie;

import org.junit.Test;


//done by: Aya Kakizaki
//test cases that tests skeleton's movement
public class EnemyMovementTest1 {
    @Test
    public void testMove() {
        Skeleton skeleton = new Skeleton(1, 10, 1, 1);
        int initialRow = skeleton.getRow();
        //wait
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        skeleton.move();
        int updatedRow = skeleton.getRow();
        assertEquals(initialRow + 1, updatedRow);
    }
    @Test
    public void testMoveWithoutWaiting() {
        Skeleton skeleton = new Skeleton(1, 10, 1, 1);
        int initialRow = skeleton.getRow();
        skeleton.move();
        int updatedRow = skeleton.getRow();
        assertEquals(initialRow, updatedRow);
    }

    //Josh Forden
    //Test movement for Zombie
    @Test
    public void testZombieMovement() {
        Zombie zombie = new Zombie(2, 3, 5, 11);
        int initialRow = zombie.getRow();


        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        zombie.move();
        int updatedRow = zombie.getRow();
        //Tests Zombie moved down by movementSpeed after waiting
        assertEquals(initialRow + zombie.getMovementSpeed(), updatedRow);

        //Tests Zombie didn't move after not waiting
        zombie.move();
        assertEquals(zombie.getRow(), updatedRow);
    }

    @Test
    public void testVampireMovement() {
        Vampire vampire = new Vampire(2, 2, 6, 1);
        int initialCol = vampire.getColumn();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        vampire.move();
        int updatedCol = vampire.getColumn();
        //Tests Vampire moved right by movementSpeed after waiting
        assertEquals(initialCol + vampire.getMovementSpeed(), updatedCol);

        //Tests Vampire didn't move after not waiting
        vampire.move();
        assertEquals(vampire.getColumn(), updatedCol);
    }

    @Test
    public void testGhostMovement() {
        Ghost ghost = new Ghost(1, 2, 12, 13);
        int initialCol = ghost.getColumn();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ghost.move();
        int updatedCol = ghost.getColumn();
        //Tests Ghost moved left by movementSpeed after waiting
        assertEquals(initialCol + ghost.getMovementSpeed(), updatedCol);

        //Tests Ghost didn't move after not waiting
        ghost.move();
        assertEquals(ghost.getColumn(), updatedCol);
    }





}
