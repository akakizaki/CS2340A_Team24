package com.example.team24dungeoncrawler.model;

public interface Observable {
    public void addObserver(PlayerObserver observer);
    public void removeObserver(PlayerObserver observer);
    public void removeObservers();
    public void notifyObservers();
}
