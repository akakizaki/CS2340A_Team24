package com.example.team24dungeoncrawler.model;

import android.util.Log;

import java.util.Random;

public class Skeleton extends Enemy {
    private int row;
    private int column;
    private int direction;
    private long lastMoveTime;
    private Enemy enemy;


    public Skeleton(int movementSpeed, int damage, int row, int column) {
        super(movementSpeed, damage, row, column);
        lastMoveTime = System.currentTimeMillis();
    }

@Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime >= 1000) { //check if 1 second has passed
            lastMoveTime = currentTime;
            int currentRow = super.getRow();
            currentRow += 1; //move 1 tile down
            super.setRow(currentRow);
        }
    }


}



