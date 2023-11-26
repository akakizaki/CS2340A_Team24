package com.example.team24dungeoncrawler.Sprint4TestUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import com.example.team24dungeoncrawler.model.Ghost;

import com.example.team24dungeoncrawler.model.Zombie;

import org.junit.Test;

public class PdTest4 {


    @Test
    public void ghostWall() {
        Ghost ghost = new Ghost(1, 2, 10, 11);
        int maxcol = 18;
        int pastwall = 0;

        //Moves ghost 10 times
        //If ghost moves past wall at any time
        //counter is updated
        // if counter is above 0 then boolean
        // pastwallTest is true and Test fails
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ghost.move();
            if (ghost.getColumn() - maxcol > 0) {
                pastwall++;
            }

        }
        boolean pastWallTest = pastwall > 0;
        assertFalse(pastWallTest);

    }

    @Test
    public void zombieWall() {
        Zombie zombie = new Zombie(1, 2, 12, 6);
        int maxrow = 18;
        int pastwall = 0;

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            zombie.move();
            if (zombie.getRow() - maxrow > 0) {
                pastwall++;
            }

        }
        Boolean pastWallTest = false;
        if (pastwall > 0) {
            pastWallTest = true;
        }
        assertEquals(pastWallTest, false);

    }

}
