package server.model.board;


public class LivingRoom {


    private boardToken[][] Board = new boardToken[9][9];

    public LivingRoom() {
        for(int i =0; i<3||i>5 ;i++ ) {
            for (int j = 0; j <3; j++) {
                Board[i][j].setCategory(boardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
        for(int i =0; i<3||i>5 ;i++ ) {
            for (int j = 6; j <9; j++) {
                Board[i][j].setCategory(boardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
        //SET THREE
        Board[2][2].setCategory(boardToken.boardTokenCategory.THREE);
        Board[2][6].setCategory(boardToken.boardTokenCategory.THREE);
        Board[6][2].setCategory(boardToken.boardTokenCategory.THREE);
        Board[6][6].setCategory(boardToken.boardTokenCategory.THREE);
        Board[0][3].setCategory(boardToken.boardTokenCategory.THREE);
        Board[3][8].setCategory(boardToken.boardTokenCategory.THREE);
        Board[0][5].setCategory(boardToken.boardTokenCategory.THREE);
        Board[8][5].setCategory(boardToken.boardTokenCategory.THREE);

        //SET FOUR
    }

    public void Start(int numPlayers){

       }

    public void restore(){

    }
}
