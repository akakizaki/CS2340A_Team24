package com.example.team24dungeoncrawler.model;

public class HealthPowerUp extends PowerUp {

    public HealthPowerUp(Player player) {
        super(player);
    }

    @Override
    public int getHealth() {
        return super.getHealth() + 25;
    }
}
