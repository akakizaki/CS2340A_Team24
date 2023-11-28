package com.example.team24dungeoncrawler.model;

public abstract class PowerUp implements PlayerObserver{
    protected int row;
    protected int column;
    protected boolean visible;
    public PowerUp (int row, int column) {
        this.row = row;
        this.column = column;
        this.visible = true;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean getVisibility() {return visible;}

    public void negateVisibility() {this.visible = false;}


}
