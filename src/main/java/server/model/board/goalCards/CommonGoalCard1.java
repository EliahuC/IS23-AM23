package server.model.board.goalCards;
import server.model.board.ItemTile;
import server.model.player.BookShelf;

import java.util.List;

public class CommonGoalCard1 extends CommonGoalCard implements CheckCommonGoalCard {
    private List<ItemTile> validGroups;
    @Override
    //Six groups of 2 adjacent tiles.
    public void checkGoal(BookShelf bs) {
        for(int j = 5; j >= 0; j--){
            for(int i=0; i<=4; i++){
                if (!validGroups.contains(bs.getTile(i,j))){
                    ItemTile adjacentTile = hasAdjacent(bs.getTile(i,j));
                    if(adjacentTile != null && !validGroups.contains(adjacentTile)){
                        if(hasAdjacent(adjacentTile, bs.getTile(i,j)) == null){
                            validGroups.add(bs.getTile(i,j));
                            validGroups.add(adjacentTile);
                        }
                    }
                }
            }
        }
        if (validGroups.size() >= 12)
            increaseNumCompleted();
    }

    public ItemTile hasAdjacent(ItemTile selectedTile){
        //It does check if selectedTile has only one adjacent tile with the same category.
    }

    public ItemTile hasAdjacent(ItemTile checkingTile, ItemTile selectedTile){
        //It does check if checkingTile has no adjacent tiles with the same category unless for selectedTile.
    }
}