package com.example.team24dungeoncrawler.model;

public class ScorePowerUp extends PowerUp {

    public ScorePowerUp(Player player) {
        super(player);
    }

    @Override
    public int getScore() {
        return super.getScore() + 10;
    }
}
