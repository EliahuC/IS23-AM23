package server.model.board;

import it.polimi.ingsw.boardToken;

public class LivingRoom {


    private boardToken[][] Board = new boardToken[9][9];

    public LivingRoom() {
        for(int i =0; i<=9 ;i++ ) {
            for (int j = 0; j <= 9; j++) {
                this.Board[i][j] = boardToken.UNAVAILABLE;
            }
        }
    }

    public void Start(int numPlayers){

       }

    public void restore(){

    }
}
