package com.example.team24dungeoncrawler.model;


public class Ghost extends Enemy {

    private long lastMoveTime;
    private int movementSpeed;
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
                super.setColumn(newCol);
            } else {
                movementSpeed = -movementSpeed;
                super.setColumn(currentCol + movementSpeed);
            }
        }
    }

    private boolean isValidMove(int newCol) {
        return newCol >= 0 && newCol < MAX_COL;
    }


}
