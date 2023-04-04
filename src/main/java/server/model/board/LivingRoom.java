package server.model.board;
import server.Launcher;
import server.model.GameChecker;

public class LivingRoom {
    private static int MAX_Row=9;
    private static int MAX_Column=9;

    private BoardToken[][] Board = new BoardToken[MAX_Row][MAX_Column];
    private Launcher L;
    private Bag bag;
    private GameChecker G;

    public LivingRoom() {

        //SET UNAVAILABLE
        SetUnavailable();

        //SET THREE
        SetThree();

        //SET FOUR
        SetFour();

    }

    public void SetUnavailable() {
        for (int i = 0; i < 3 || i > 5; i++) {
            for (int j = 0; j < 3; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
        for (int i = 0; i < 3 || i > 5; i++) {
            for (int j = 6; j < 9; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
        Board[3][0].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[0][5].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[5][8].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[8][3].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
    }

    public void SetThree() {
        Board[2][2].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[2][6].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[6][2].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[6][6].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[0][3].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[3][8].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[0][5].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[8][5].setCategory(BoardToken.boardTokenCategory.THREE);
    }

    public void SetFour() {
        Board[0][4].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[1][5].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[3][1].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[4][0].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[4][8].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[5][7].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[7][3].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[8][4].setCategory(BoardToken.boardTokenCategory.FOUR);
    }

    public void Start(int numPlayers) {
        for (int i = 0; i < MAX_Row; i++) {
            for (int j = 0; j < MAX_Column; j++) {
                putTile(i, j);
            }
        }
    }


    public void restore() {
        if (G.isRestorable(Board)) {
            for (int i = 0; i < MAX_Row; i++) {
                for (int j = 0; j < MAX_Column; j++) {
                    if (Board[i][j].getTile() == null) {
                        Board=putTile(i, j);
                    }
                }
            }
        }
    }


    public BoardToken[][] putTile(int i, int j) {
        switch (L.getNumPlayers()) {
            case 2 -> {
                if (Board[i][j].getCategory() == BoardToken.boardTokenCategory.NORMAL)
                    Board[i][j].setTile(bag.extract());
            }
            case 3 -> {
                if (Board[i][j].getCategory() == (BoardToken.boardTokenCategory.NORMAL) || Board[i][j].getCategory() == (BoardToken.boardTokenCategory.THREE))
                    Board[i][j].setTile(bag.extract());
            }
            case 4 -> {
                if (Board[i][j].getCategory() != BoardToken.boardTokenCategory.UNAVAILABLE)
                    Board[i][j].setTile(bag.extract());
            }
        }
        return Board;
    }
        public BoardToken[][] getBoard() {
            return Board;
        }

}
