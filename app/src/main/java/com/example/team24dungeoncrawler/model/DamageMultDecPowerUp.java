package com.example.team24dungeoncrawler.model;

public class DamageMultDecPowerUp extends PowerUp {

    public DamageMultDecPowerUp(Player player) {
        super(player);
    }

    @Override
    public double getDamageMultiplier() {
        return super.getDamageMultiplier() - 0.2;
    }

}
