package com.example.team24dungeoncrawler;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private String direction;
    private int speed;
    private int score;
    private double damageMultiplier;
    private static Player instance;
    private List<Attempt> attemptHistory;


    public Player(String name, String difficulty) {
        this.name = name;
        this.direction = "";
        this.speed = 0;
        this.score = 0;
        this.attemptHistory = new ArrayList<>();
        this.score = 0;

        // Set health and damageMultiplier based on the selected difficulty
        switch (difficulty) {
        case "Easy":
            this.health = 150;
            this.damageMultiplier = 0.8;
            break;
        case "Medium":
            this.health = 100;
            this.damageMultiplier = 1.0;
            break;
        case "Hard":
            this.health = 50;
            this.damageMultiplier = 1.2;
            break;
        default:
            this.health = 100; // Default values for invalid difficulty
            this.damageMultiplier = 1.0;
            break;
        }
    }
    static Player getInstance(String name, String difficulty) {
        if (instance == null) {
            instance = new Player(name, difficulty);
        }
        return instance;
    }

    // Getter and setter methods for the attributes
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public double getDamageMultiplier() {
        return damageMultiplier;
    }

}