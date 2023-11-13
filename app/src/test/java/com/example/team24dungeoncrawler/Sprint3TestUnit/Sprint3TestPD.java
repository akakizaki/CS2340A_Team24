package com.example.team24dungeoncrawler.Sprint3TestUnit;

import com.example.team24dungeoncrawler.model.Player;
import com.example.team24dungeoncrawler.viewmodels.MainGameActivity;

import static org.junit.Assert.assertEquals;


import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class Sprint3TestPD {

    private Player player;
    MainGameActivity mga;
    KeyEvent keyPressEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_D);
    @Before
    public void playerConstructor() {
        // Create a new Player instance before each test
        player = Player.getInstance("TestPlayer", "Medium");

    }

    @Test
    public void testplayerColision() {
        assertEquals(player.getHealth(), 100);
    }

    @Test
    public void testScreenSwitch() {
        assertEquals(player.getSpeed(),0);
    }

}
