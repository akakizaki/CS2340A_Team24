package com.example.team24dungeoncrawler.model;

public class DamageMultDecPowerUp extends PowerUp {

    public DamageMultDecPowerUp(int row, int column) {
        super(row, column);
    }

    public void update(Player player){
        int playerRow = player.getRow();
        int playerCol = player.getCol();
        int powerUpRow = this.getRow();
        int powerUpCol = this.getColumn();

        if (playerRow == powerUpRow && playerCol == powerUpCol && this.getVisibility()) {{
            DamageMultDecPowerUpDecorator powerUp = new DamageMultDecPowerUpDecorator(player);
            player.setDamageMultiplier(powerUp.getDamageMultiplier());
        }}
    }
}
