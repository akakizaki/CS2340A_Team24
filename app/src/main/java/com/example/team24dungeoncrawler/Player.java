package com.example.team24dungeoncrawler;
public class Player {
    private String name;
    private int health;
    private String direction;
    private int speed;
    private int score;
    private double damageMultiplier;

    public Player(String name, String difficulty) {
        this.name = name;
        this.direction = "";
        this.speed = 0;
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

    // Getter and setter methods for the attributes
    public String getName() {
        return name;
    }
}