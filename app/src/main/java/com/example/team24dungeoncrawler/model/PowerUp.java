package com.example.team24dungeoncrawler.model;

public abstract class PowerUp extends Player {
    protected Player decoratedPlayer;

    public PowerUp(Player decoratedPlayer) {
        super(decoratedPlayer.getName(),decoratedPlayer.getDifficulty()); // Assuming default difficulty
        this.decoratedPlayer = decoratedPlayer;
    }


    @Override
    public int getScore() {
        return decoratedPlayer.getScore();
    }

    @Override
    public int getHealth() {
        return decoratedPlayer.getHealth();
    }


}
