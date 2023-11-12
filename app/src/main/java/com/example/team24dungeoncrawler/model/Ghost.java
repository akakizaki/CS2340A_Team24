package com.example.team24dungeoncrawler.model;

import android.util.Log;

public class Ghost extends Enemy{

    private int row;
    private int column;
    private int direction;
    private long lastMoveTime;
    private Enemy enemy;

    private int movementSpeed;
    private boolean movingRight = true;
    private  static final int MAX_COL = 18;


    public Ghost(int movementSpeed, int damage, int row, int column) {
        super(movementSpeed, damage, row, column);
        lastMoveTime = System.currentTimeMillis();
        this.movementSpeed = movementSpeed;
        // super(3, 5, 12, 5);

    }
  
    @Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime >= 1000) { //check if 1 second has passed
            lastMoveTime = currentTime;
            int currentCol = super.getColumn();
            int newCol = currentCol + this.movementSpeed;
            if (newCol > 0 && newCol < MAX_COL) {
                super.setRow(newCol);
            } else {
                movementSpeed = -movementSpeed;
                super.setRow(currentCol + movementSpeed);
            }
        }
    }
}
