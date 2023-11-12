package com.example.team24dungeoncrawler.model;

public class Skeleton extends Enemy {
    private int row;
    private int column;
    private int direction;
    private long lastMoveTime;
    private int movementSpeed;
    private Enemy enemy;


    public Skeleton(int movementSpeed, int damage, int row, int column) {
        super(movementSpeed, damage, row, column);
        this.movementSpeed = movementSpeed;
        lastMoveTime = System.currentTimeMillis();
    }

    @Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime >= 1000) { // check if 1 second has passed
            lastMoveTime = currentTime;
            int currentRow = super.getRow();
            int newRow = currentRow + this.movementSpeed;

            if (newRow > 0 && newRow < 19) {
                super.setRow(newRow);
            } else {
                movementSpeed = -movementSpeed;
                super.setRow(currentRow + movementSpeed);
            }
        }
    }


}



