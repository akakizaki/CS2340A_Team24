package com.example.team24dungeoncrawler.model;

abstract class PlayerDecorator implements PlayerInterface {
    private final PlayerInterface decoratedPlayer;

    public PlayerDecorator(PlayerInterface player) {
        this.decoratedPlayer = player;
    }

    @Override
    public void update(Player player) {}
}
