package com.example.team24dungeoncrawler.model;


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
            int newRow = currentRow + this.movementSpeed;
            if (newRow > 0 && newRow < MAX_ROWS) {
                super.setRow(newRow);
            } else {
                movementSpeed = -movementSpeed;
                super.setRow(currentRow + movementSpeed);
            }
        }
    }

    private boolean isValidMove(int newRow) {
        return newRow >= 0 && newRow < MAX_ROWS;
    }
}
