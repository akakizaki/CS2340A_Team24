package com.example.team24dungeoncrawler.Sprint4TestUnit;

import static org.junit.Assert.assertEquals;

import com.example.team24dungeoncrawler.model.Ghost;

import org.junit.Assert;
import org.junit.Test;

public class PDTest4 {
    @Test
    public void GhostWall() {
        Ghost ghost = new Ghost(1, 2, 10, 11);
        int maxcol = 18;
        int initialCol = ghost.getColumn();
        int pastwall = 0;

        //Moves ghost 10 times
        //If ghost moves past wall at any time
        //counter is updated
        // if counter is above 0 then boolean
        // pastwallTest is true and Test fails
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ghost.move();
            if (ghost.getColumn() - 18 > 0) {
                pastwall++;
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Boolean pastWallTest = false;
        if (pastwall > 0 ) {
            pastWallTest = true;
        }
        assertEquals(pastWallTest, false);

    }
}
