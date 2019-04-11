package com.example.trivia;

public class HighScore {
    private String name, highScore;

    public HighScore(String highScore, String name) {
        this.highScore = highScore;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getHighScore() {
        return highScore;
    }

    public void setName(String name) {
        this.name = name;
    }
}
