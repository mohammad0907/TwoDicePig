package com.example.cis436p1_twodicepig;

public class Player {




    private int score;
    private int turnTotal;
    private String playerName;

    public Player(String name){
        this.score = 0;
        this.turnTotal = 0;
        this.playerName = name;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTurnTotal() {
        return turnTotal;
    }

    public void setTurnTotal(int turnTotal) {
        this.turnTotal = turnTotal;
    }

}
