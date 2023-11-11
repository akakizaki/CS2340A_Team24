package com.example.team24dungeoncrawler.model;

import android.util.Log;

public class Zombie extends Enemy {
    private int row;
    private int column;
    private int direction;
    private Enemy enemy;

    private int movementSpeed;

    private long lastMoveTime;
    private static final int MAX_ROWS = 18;
    private boolean movingDown = true;

    public Zombie(int movementSpeed, int damage, int row, int column) {
        super(movementSpeed, damage, row, column);
        this.movementSpeed = movementSpeed;
        lastMoveTime = System.currentTimeMillis();
    }
    
    @Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime >= 1000) { //check if 1 second has passed
            lastMoveTime = currentTime;
            int currentRow = super.getRow();
            int newRow;

            if (movingDown) {
                newRow = currentRow + 1; // move 1 tile down

                if (isValidMove(newRow)) {
                    super.setRow(newRow);
                    Log.d("zombie move", "done");
                } else {
                    //reverse
                    movingDown = false;
                }
            } else {
                newRow = currentRow - 1; // move 1 tile up
                if (isValidMove(newRow)) {
                    super.setRow(newRow);
                } else {
                    movingDown = true;
                }
            }
        }
    }

    private boolean isValidMove(int newRow) {
        return newRow >= 0 && newRow < MAX_ROWS;
    }
}
