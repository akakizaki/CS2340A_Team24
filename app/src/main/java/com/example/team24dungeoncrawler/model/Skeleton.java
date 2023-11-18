package com.example.team24dungeoncrawler.model;

import com.example.team24dungeoncrawler.viewmodels.MainActivity;

public class Skeleton extends Enemy {
    private int row;
    private int column;
    private int direction;
    private long lastMoveTime;
    private int movementSpeed;
    private Enemy enemy;
    private MainActivity mainActivity;
    private static final int MAX_ROWS = 19;
    private int movementSpeed;


    public Skeleton(int movementSpeed, int damage, int row, int column) {
        super(movementSpeed, damage, row, column);
        this.movementSpeed = movementSpeed;
        lastMoveTime = System.currentTimeMillis();
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime >= 1000) { // check if 1 second has passed
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
