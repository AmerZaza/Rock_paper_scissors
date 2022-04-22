package com.locationsaz.sps3;

public class Round {
    Game game;
    Element player1Select;
    Element player2Select;

    Player winner;

    public Round() {

    }

    public Round(Game game, Element player1Select, Element player2Select, Player winner) {
        this.game = game;
        this.player1Select = player1Select;
        this.player2Select = player2Select;
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }

    public Player  getTheWinner(Player p1, Player p2, Element player1Select, Element player2Select){

        Player rWinner = new Player();

        if(player1Select.getElementName().equalsIgnoreCase(player2Select.getElementName())){

           // System.out.println("No winner");
            rWinner = new Player("X") ;

        }else if(player2Select.getStronger().getElementName().equalsIgnoreCase(player1Select.getElementName()) ){
            rWinner = p1 ;
        }else if(player2Select.getWeaker().getElementName().equalsIgnoreCase(player1Select.getElementName()) ){
            rWinner = p2 ;
        }else {
           // System.out.println("?????????");
        }

        return rWinner;

    }
}
