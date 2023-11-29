package com.example.team24dungeoncrawler.model;

public class DamageMultDecPowerUpDecorator extends PowerUpDecorator {

    public DamageMultDecPowerUpDecorator(Player player) {
        super(player);
    }

    @Override
    public double getDamageMultiplier() {
        return super.getDamageMultiplier() - 0.2;
    }
}
