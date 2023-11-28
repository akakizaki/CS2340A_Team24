package com.example.team24dungeoncrawler.model;

public class HealthPowerUpDecorator extends PowerUpDecorator {

    public HealthPowerUpDecorator(Player player) {
        super(player);
    }

    @Override
    public int getHealth() {
        return super.getHealth() + 25;
    }
}
