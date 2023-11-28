package com.example.team24dungeoncrawler.model;

public abstract class PowerUp implements PlayerObserver{
    protected int row;
    protected int column;
    public PowerUp (int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


}