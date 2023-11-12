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
    private boolean movingDown = true;


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
            int newRow;

            if (movingDown) {
                newRow = currentRow + 1; //move 1 tile down
                if (isValidMove(newRow)) {
                    super.setRow(newRow);
                } else {
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


    @Override
    public void update(Player player) {
        int playerRow = player.getRow();
        int playerCol = player.getCol();
        int enemyRow = this.getRow();
        int enemyCol = this.getColumn();

        if (playerRow == enemyRow && playerCol == enemyCol) {
            player.decreaseHealth((int) (this.getDamage() * player.getDamageMultiplier()));
        }
    }

    private boolean isValidMove(int newRow) {
        return newRow >= 0 && newRow < MAX_ROWS;

    }
}
