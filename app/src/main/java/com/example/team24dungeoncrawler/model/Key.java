package com.example.team24dungeoncrawler.model;

public class Key implements PlayerObserver {
    private int row;
    private int column;
    private boolean visible;

    public Key(int row, int column) {
        this.row = row;
        this.column = column;
        this.visible = true;
    }
    public int getRow() {return row;}
    public int getColumn() {return column;}
    public void update(Player player) {
        int playerRow = player.getRow();
        int playerCol = player.getCol();
        int keyRow = this.getRow();
        int keyCol = this.getColumn();

        if (playerRow == keyRow && playerCol == keyCol && visible) {
            player.setHasKey(true);
        }
    }

    public boolean isVisibile() {return visible;}

    public void negateVisibility() {this.visible = false;}
}
