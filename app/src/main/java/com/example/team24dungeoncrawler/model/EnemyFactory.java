package com.example.team24dungeoncrawler.model;

import android.util.Log;

public class EnemyFactory {
    public static Enemy createEnemy(int enemyType, int movementSpeed, int damage, int row, int column) {
        switch (enemyType) {
<<<<<<< HEAD
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
=======
            case 1:
                return new Skeleton(movementSpeed, damage, row, column);
            case 2:
                return new Vampire();
            default:
                return null;
>>>>>>> main
        }
    }
}
