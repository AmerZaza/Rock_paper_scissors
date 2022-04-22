package com.locationsaz.sps3;

public class Game {
    Integer gameID;
    Player player1;
    Player player2;

    public Game(Integer gameID, Player player1, Player player2) {
        this.gameID = gameID;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
