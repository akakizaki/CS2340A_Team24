package com.example.team24dungeoncrawler.model;

public class ScorePowerUp extends PowerUp {

    public ScorePowerUp(int row, int column) {
        super(row, column);
    }

    public void update(Player player){
        int playerRow = player.getRow();
        int playerCol = player.getCol();
        int powerUpRow = this.getRow();
        int powerUpCol = this.getColumn();

        if (playerRow == powerUpRow && playerCol == powerUpCol && this.getVisibility()) {{
            ScorePowerUpDecorator powerUp = new ScorePowerUpDecorator(player);
            player.setScore(powerUp.getScore());
        }}
    }
}
