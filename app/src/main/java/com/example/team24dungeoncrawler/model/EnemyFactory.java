package com.example.team24dungeoncrawler.model;

public class EnemyFactory {
    public static Enemy createEnemy(int enemyType) {
        switch (enemyType) {
        case 1:
            return new Skeleton();
        case 2:
            return new Vampire();
        case 3:
            return new Ghost();
        case 4:
            return new Zombie();
        default:
            return null;
        }
    }
}
