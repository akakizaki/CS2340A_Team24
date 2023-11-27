package com.example.team24dungeoncrawler.model;

public class HealthDecorator extends PlayerDecorator {

    public HealthDecorator(PlayerInterface player) {
        super(player);
    }

    @Override
    public void update(Player player) {
        player.setHealth(player.getHealth() + 25);
    }
}
