package com.example.team24dungeoncrawler.model;

public class Zombie extends Enemy {
    private int row;
    private int column;
    private int direction;
    private Enemy enemy;

    private int movementSpeed;

    private long lastMoveTime;

    public Zombie(int movementSpeed, int damage, int row, int column) {
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
            int currentRow = super.getRow();
            currentRow += movementSpeed; //move down by movementSpeed
            super.setRow(currentRow);
        }
    }

}
