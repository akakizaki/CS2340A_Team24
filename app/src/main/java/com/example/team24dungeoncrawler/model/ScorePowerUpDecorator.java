package com.example.team24dungeoncrawler.model;

public class ScorePowerUpDecorator extends PowerUpDecorator {

    public ScorePowerUpDecorator(Player player) {
        super(player);
    }

    @Override
    public int getScore() {
        return super.getScore() + 10;
    }
}
