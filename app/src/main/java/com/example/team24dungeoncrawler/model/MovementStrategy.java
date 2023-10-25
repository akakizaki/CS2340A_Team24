package com.example.team24dungeoncrawler.model;

public interface MovementStrategy {
    void move(Player player, int keyCode, int[][] tilemap);
}
