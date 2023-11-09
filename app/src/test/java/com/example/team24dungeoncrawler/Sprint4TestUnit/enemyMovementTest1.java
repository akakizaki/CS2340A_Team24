package com.example.team24dungeoncrawler.Sprint4TestUnit;



import static org.junit.Assert.assertEquals;

import com.example.team24dungeoncrawler.model.Skeleton;

import org.junit.Test;


//done by: Aya Kakizaki
//test cases that tests skeleton's movement
public class enemyMovementTest1 {
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





}
