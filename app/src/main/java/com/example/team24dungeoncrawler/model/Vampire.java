package com.example.team24dungeoncrawler.model;



public class Vampire extends Enemy {

    private int row;
    private int column;
    private int direction;
    private Enemy enemy;

    private int movementSpeed;

    private long lastMoveTime;

    public Vampire(int movementSpeed, int damage, int row, int column) {
        super(movementSpeed, damage, row, column);
        this.movementSpeed = movementSpeed;
        lastMoveTime = System.currentTimeMillis();
    }

    @Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime >= 1000) { // check if 1 second has passed
            lastMoveTime = currentTime;

            int currentCol = super.getColumn();
            int newCol = currentCol + movementSpeed;

            if (newCol >= 0 && newCol < 19) {
                super.setColumn(newCol);
            } else {
                movementSpeed = -movementSpeed;
                super.setColumn(currentCol + movementSpeed);
            }
        }
    }

}
