package it.polimi.ingsw.model.board;

/**ù
 * @author Eliahu Cohen
 * class that rapresent the board token on the livingroom
 */
public class BoardToken {
    private ItemTile Tile ;
    private boardTokenCategory category;
    private BoardToken[][] associatedBoard;
    private int column;
    private int row;

    public BoardToken() {
        this.Tile = null;
        this.category=boardTokenCategory.NORMAL;
    }

    public boardTokenCategory getCategory() {
        return category;
    }

    public void setCategory(boardTokenCategory category) {
        this.category = category;
    }

    public ItemTile getTile() {
        return Tile;
    }

    public void setTile(ItemTile tile) {
        Tile = tile;
    }

    public enum boardTokenCategory {
        NORMAL,
        THREE,
        FOUR,
        UNAVAILABLE
    }

    public BoardToken[][] getBoard(){
        return associatedBoard;
    }

    public void setBoard(BoardToken[][] associatedBoard){
        this.associatedBoard=associatedBoard;
    }

    public int getCol(){
        return column;
    }

    public void setCol(int column){
        this.column = column;
    }

    public int getRow(){
        return row;
    }

    public void setRow(int row){
        this.row = row;
    }
    
    public void freeTile(){
        Tile=null;
    }

    public void print(){
        if(this.getCategory()==boardTokenCategory.UNAVAILABLE)
            System.out.print("   ");
        else if(this.getTile()==null)
            System.out.print(" □ ");
        else
            this.getTile().print();
    }
}
