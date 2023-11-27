package com.example.team24dungeoncrawler.model;

public abstract class PowerUp implements PlayerInterface {
    protected PlayerInterface decoratedPlayer;

    public PowerUp(PlayerInterface decoratedPlayer) {

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
