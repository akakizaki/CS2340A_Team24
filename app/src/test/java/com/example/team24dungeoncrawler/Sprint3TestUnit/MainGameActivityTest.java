package com.example.team24dungeoncrawler.Sprint3TestUnit;
import org.junit.Test;

import com.example.team24dungeoncrawler.model.Player;
import com.example.team24dungeoncrawler.viewmodels.MainGameActivity;
import static org.junit.Assert.*;

import android.view.KeyEvent;


public class MainGameActivityTest {
    // Simulate a key press event (e.g., KEYCODE_D for the 'D' key):
    private KeyEvent keyPressEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_D);

    // Simulate a key release event (e.g., KEYCODE_D for the 'D' key):
    private KeyEvent keyReleaseEvent = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_D);

    @Test
    public void testMoveRight() {
        // Create an instance of MainGameActivity
        MainGameActivity gameActivity = new MainGameActivity();
        Player player = Player.getInstance("player1", "");

        // Set the player's initial position
        player.setRow(3);
        player.setCol(3);

        // Simulate a key press event to move the player right (e.g., KeyEvent.KEYCODE_D)
        gameActivity.onKeyDown(keyPressEvent.getKeyCode(), keyPressEvent);

        // Assert that the player's position has been updated correctly
        assertEquals(3, player.getRow());
        assertEquals(4, player.getCol());
    }

    @Test
    public void testMoveUp() {
        // Create an instance of MainGameActivity
        MainGameActivity gameActivity = new MainGameActivity();
        Player player = Player.getInstance("player", "hard");

        // Set the player's initial position
        player.setRow(3);
        player.setCol(3);

        // Simulate a key press event to move the player up (e.g., KeyEvent.KEYCODE_W)
        gameActivity.onKeyDown(keyPressEvent.getKeyCode(), keyPressEvent);

        // Assert that the player's position has been updated correctly
        assertEquals(2, player.getRow());
        assertEquals(3, player.getCol());
    }
}



