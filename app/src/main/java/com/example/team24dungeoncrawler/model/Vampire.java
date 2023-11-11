package com.example.team24dungeoncrawler.model;

import android.util.Log;

import java.util.Random;

public class Vampire extends Enemy {

    private int row;
    private int column;
    private int direction;
    private Enemy enemy;

    private int movementSpeed;

    private long lastMoveTime;
    private boolean movingRight = true;
    private  static final int MAX_COL = 19;

    public Vampire(int movementSpeed, int damage, int row, int column) {
        super(movementSpeed, damage, row, column);
        this.movementSpeed = movementSpeed;
        lastMoveTime = System.currentTimeMillis();

    }
    @Override
    public void move() {
       //super.move();
      long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime >= 1000) { //check if 1 second has passed
            lastMoveTime = currentTime;
            int currentCol = super.getColumn();
            int newCol;
            if (movingRight) {
                newCol = currentCol + 1;
                if (isValidMove(newCol)) {
                    super.setColumn(newCol);
                    Log.d("vampire move", "done");
                } else {
                    movingRight = false;
                }
            } else {
                newCol = currentCol - 1; // move 1 tile up
                if (isValidMove(newCol)) {
                    super.setColumn(newCol);
                } else {
                    movingRight = true;
                }
            }
          }
    }
    private boolean isValidMove(int newCol) {
        return newCol >= 0 && newCol < MAX_COL;
    }
}
