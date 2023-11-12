package com.example.team24dungeoncrawler.model;

public class Ghost extends Enemy{

    private int row;
    private int column;
    private int direction;
    private long lastMoveTime;
    private Enemy enemy;

    private int movementSpeed;


    public Ghost(int movementSpeed, int damage, int row, int column) {
        super(movementSpeed, damage, row, column);
        lastMoveTime = System.currentTimeMillis();
        this.movementSpeed = movementSpeed;
        // super(3, 5, 12, 5);

    }
  
    @Override
    public void move() {
        //movement

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime >= 1000) { //check if 1 second has passed
            lastMoveTime = currentTime;
            int currentCol = super.getColumn();
            currentCol -= movementSpeed; //move left by movementSpeed
            super.setColumn(currentCol);
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
}
