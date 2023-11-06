package com.example.team24dungeoncrawler.model;

import android.util.Log;

public class EnemyFactory {
    public static Enemy createEnemy(int enemyType, int movementSpeed, int damage, int row, int column) {
        switch (enemyType) {
            case 1:
                return new Skeleton(movementSpeed, damage, row, column);
            case 2:
                return new Vampire();
            default:
                return null;
        }
    }
}
