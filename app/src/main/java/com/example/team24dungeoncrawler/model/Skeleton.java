package com.example.team24dungeoncrawler.model;

import android.util.Log;

import com.example.team24dungeoncrawler.viewmodels.MainActivity;

import java.util.Random;

public class Skeleton extends Enemy {
    private int row;
    private int column;
    private int direction;
    private long lastMoveTime;
    private Enemy enemy;
    private MainActivity mainActivity;
    private static final int MAX_ROWS = 19;
    private int movementSpeed;


    public Skeleton(int movementSpeed, int damage, int row, int column) {
        super(movementSpeed, damage, row, column);
        lastMoveTime = System.currentTimeMillis();
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime >= 1000) { //check if 1 second has passed
            lastMoveTime = currentTime;
            int currentRow = super.getRow();
            int newRow = currentRow + this.movementSpeed;
            if (newRow > 0 && newRow < MAX_ROWS) {
                super.setRow(newRow);
            } else {
                movementSpeed = -movementSpeed;
                super.setRow(currentRow + movementSpeed);
            }
        }
    }
}
