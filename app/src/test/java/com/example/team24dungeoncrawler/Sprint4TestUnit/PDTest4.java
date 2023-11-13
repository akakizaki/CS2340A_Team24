package com.example.team24dungeoncrawler.Sprint4TestUnit;

import static org.junit.Assert.assertEquals;

import android.view.KeyEvent;

import com.example.team24dungeoncrawler.model.Ghost;
import com.example.team24dungeoncrawler.model.Player;
import com.example.team24dungeoncrawler.model.Zombie;
import com.example.team24dungeoncrawler.viewmodels.Game2activity;
import com.example.team24dungeoncrawler.viewmodels.MainGameActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

public class PDTest4 {


    @Test
    public void GhostWall() {
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
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ghost.move();
            if (ghost.getColumn() - maxcol > 0) {
                pastwall++;
            }
//            try {
//                Thread.sleep(1500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        Boolean pastWallTest = false;
        if (pastwall > 0 ) {
            pastWallTest = true;
        }
        assertEquals(pastWallTest, false);

    }

    @Test
    public void ZombieWall() {
        Zombie zombie = new Zombie(1, 2, 12, 6);
        int maxrow = 18;
        int pastwall = 0;

        //Moves zombie 10 times
        //If zombie moves past wall at any time
        //counter is updated
        // if counter is above 0 then boolean
        // pastwallTest is true and Test fails
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            zombie.move();
            if (zombie.getRow() - maxrow > 0) {
                pastwall++;
            }
//            try {
//                Thread.sleep(1500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        Boolean pastWallTest = false;
        if (pastwall > 0 ) {
            pastWallTest = true;
        }
        assertEquals(pastWallTest, false);

    }

//    @Test
//    public void ghostCollision() {
//        Game2activity gameActivity = new Game2activity();
//        KeyEvent keyPressEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_D);
//        Player player = Player.getInstance("John", "Medium");
//        gameActivity.onKeyDown(keyPressEvent.getKeyCode(), keyPressEvent);
//        System.out.println(player.getCol()+ " " + player.getRow() +" "+ player.getHealth());
//        Ghost ghost = new Ghost(1, 2, 10, 11);
//        System.out.println(ghost.getColumn()+" "+ ghost.getRow());
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ghost.move();
//
//        System.out.println(ghost.getColumn()+" "+ ghost.getRow());
//        System.out.println(player.getCol()+ " " + player.getRow() +" "+ player.getHealth());
//        //System.out.println(ghost.getColumn());
//    }
}
