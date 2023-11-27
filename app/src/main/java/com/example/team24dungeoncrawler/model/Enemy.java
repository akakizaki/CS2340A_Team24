package com.example.team24dungeoncrawler.model;


public abstract class Enemy implements EnemyObserver {
    private int movementSpeed;
    private int damage;
    private int row;
    private int column;

    private int del;
    public Enemy(int movementSpeed, int damage, int row, int column) {
        this.movementSpeed = movementSpeed;
        this.damage = damage;
        this.row = row;
        this.column = column;
        this.del = 0;
    }

    public abstract void move();

    public int getDel() {
        return del;
    }

    public void setDel(int delC) {
        del = delC;
    }
    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getMovementSpeed() {
        return this.movementSpeed;
    }

    public int getDamage() {
        return this.damage;
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

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;

    }
}