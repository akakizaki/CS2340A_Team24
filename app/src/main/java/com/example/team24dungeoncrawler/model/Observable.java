package com.example.team24dungeoncrawler.model;

public interface Observable {
    public void addObserver(EnemyObserver observer);
    public void removeObserver(EnemyObserver observer);
    public void removeObservers();
    public void notifyObservers();
}
