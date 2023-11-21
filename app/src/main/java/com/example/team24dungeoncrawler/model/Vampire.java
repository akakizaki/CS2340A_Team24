package com.example.team24dungeoncrawler.model;



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
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastMoveTime >= 1000) { //check if 1 second has passed
            lastMoveTime = currentTime;

            int currentCol = super.getColumn();
            int newCol = currentCol + movementSpeed;

            if (newCol > 0 && newCol < MAX_COL) {
                super.setColumn(newCol);
            } else {
                movementSpeed = -movementSpeed;
                super.setColumn(currentCol + movementSpeed);
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

    private boolean isValidMove(int newCol) {
        return newCol >= 0 && newCol < MAX_COL;
    }

}
