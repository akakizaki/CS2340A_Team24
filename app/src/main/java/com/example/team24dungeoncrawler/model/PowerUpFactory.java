package com.example.team24dungeoncrawler.model;

public class PowerUpFactory {
    public static PowerUp createPowerUp(int powerUpType, int row, int column) {
        switch (powerUpType) {
        case 1:
            return new HealthPowerUp(row, column);
        case 2:
            return new DamageMultDecPowerUp(row, column);
        case 3:
            return new ScorePowerUp(row, column);
        default:
            return null;
        }
    }
}
