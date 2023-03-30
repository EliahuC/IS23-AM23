package server.model.board;


public class LivingRoom {


    private boardToken[][] Board = new boardToken[9][9];

    public LivingRoom() {

        //SET UNAVAILABLE
        SetUnavailable();

        //SET THREE
       SetThree();

        //SET FOUR
       SetFour();

        }

        public void SetUnavailable(){
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
        }
    public void SetThree (){
        Board[2][2].setCategory(boardToken.boardTokenCategory.THREE);
        Board[2][6].setCategory(boardToken.boardTokenCategory.THREE);
        Board[6][2].setCategory(boardToken.boardTokenCategory.THREE);
        Board[6][6].setCategory(boardToken.boardTokenCategory.THREE);
        Board[0][3].setCategory(boardToken.boardTokenCategory.THREE);
        Board[3][8].setCategory(boardToken.boardTokenCategory.THREE);
        Board[0][5].setCategory(boardToken.boardTokenCategory.THREE);
        Board[8][5].setCategory(boardToken.boardTokenCategory.THREE);
    }
    public void SetFour(){
        Board[0][4].setCategory(boardToken.boardTokenCategory.FOUR);
        Board[1][5].setCategory(boardToken.boardTokenCategory.FOUR);
        Board[3][1].setCategory(boardToken.boardTokenCategory.FOUR);
        Board[4][0].setCategory(boardToken.boardTokenCategory.FOUR);
        Board[4][8].setCategory(boardToken.boardTokenCategory.FOUR);
        Board[5][7].setCategory(boardToken.boardTokenCategory.FOUR);
        Board[7][3].setCategory(boardToken.boardTokenCategory.FOUR);
        Board[8][4].setCategory(boardToken.boardTokenCategory.FOUR);
    }

    public void Start(int numPlayers){

       }

    public void restore(){

    }
}
