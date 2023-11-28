package com.example.team24dungeoncrawler.model;

public class HealthPowerUp extends PowerUp {
    public HealthPowerUp(int row, int column) {
        super(row, column);
    }

    public void update(Player player){
        int playerRow = player.getRow();
        int playerCol = player.getCol();
        int powerUpRow = this.getRow();
        int powerUpCol = this.getColumn();

        if (playerRow == powerUpRow && playerCol == powerUpCol && this.getVisibility()) {{
            HealthPowerUpDecorator powerUp = new HealthPowerUpDecorator(player);
            player.setHealth(powerUp.getHealth());
        }}
    }
}
