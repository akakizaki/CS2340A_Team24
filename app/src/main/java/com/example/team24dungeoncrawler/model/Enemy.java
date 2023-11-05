package com.example.team24dungeoncrawler.model;

public abstract class Enemy {
    private int movementSpeed;
    private int damage;
    private int row;
    private int column;

    public Enemy(int movementSpeed, int damage, int row, int column) {
        this.movementSpeed = movementSpeed;
        this.damage = damage;
        this.row = row;
        this.column = column;
    }

    public abstract void move();
    //movement logic

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
}