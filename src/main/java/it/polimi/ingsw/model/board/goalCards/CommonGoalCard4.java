package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Giovanni Di Lorenzo
 */

public class CommonGoalCard4 extends CommonGoalCard implements CheckCommonGoalCard {
    private final List<ItemTile> validGroups = new ArrayList<>();
    private int counterCats = 0;
    private int counterPlants = 0;
    private int counterTrophies = 0;
    private int counterFrames = 0;
    private int counterGames = 0;
    private int counterBooks = 0;
    private final Launcher L;
    public CommonGoalCard4(Launcher L){
        this.L = L;
    }

    public int getCounterCats() {
        return counterCats;
    }

    public int getCounterPlants() {
        return counterPlants;
    }

    public int getCounterTrophies() {
        return counterTrophies;
    }

    public int getCounterFrames() {
        return counterFrames;
    }

    public int getCounterGames() {
        return counterGames;
    }

    public int getCounterBooks() {
        return counterBooks;
    }

    @Override
    public void checkGoal(BookShelf bs) {
        UpperLeft(bs);
        UpperRight(bs);
        MidLeft(bs);
        MidRight(bs);
        LowerLeft(bs);
        LowerRight(bs);
        if(TwoSquares())
            increaseNumCompleted();
        else{
            FirstRow_SecondRow1(bs);
            FirstRow_SecondRow2(bs);
            SecondRow_ThirdRow1(bs);
            SecondRow_ThirdRow2(bs);
            SecondRow_ThirdRow3(bs);
            SecondRow_ThirdRow4(bs);
            ThirdRow_FourthRow1(bs);
            ThirdRow_FourthRow2(bs);
            FourthRow_FifthRow1(bs);
            FourthRow_FifthRow2(bs);
            FourthRow_FifthRow3(bs);
            FourthRow_FifthRow4(bs);
            FifthRow_SixthRow1(bs);
            FifthRow_SixthRow2(bs);
            if(TwoSquares())
                increaseNumCompleted();
        }
    }
    private void UpperLeft(BookShelf bs) {
        if (NoItemsNull(bs, 0, 0)) {
            if ((bs.getTile(0, 0).getCategory() == bs.getTile(0, 1).getCategory()) &&
                    (bs.getTile(0, 0).getCategory() == bs.getTile(1, 0).getCategory()) &&
                    (bs.getTile(0, 0).getCategory() == bs.getTile(1, 1).getCategory())) {
                switch (bs.getTile(0, 0).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(0, 0));
                validGroups.add(bs.getTile(0, 1));
                validGroups.add(bs.getTile(1, 0));
                validGroups.add(bs.getTile(1, 1));
            }
        }
    }
    private void UpperRight(BookShelf bs){
        if(NoItemsNull(bs,0,BookShelf.getMAX_Column()-2)) {
            if ((bs.getTile(0, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(0, BookShelf.getMAX_Column() - 1).getCategory()) &&
                    (bs.getTile(0, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(1, BookShelf.getMAX_Column() - 2).getCategory()) &&
                    (bs.getTile(0, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(1, BookShelf.getMAX_Column() - 1).getCategory())) {
                switch (bs.getTile(0, BookShelf.getMAX_Column() - 2).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(0, BookShelf.getMAX_Column() - 2));
                validGroups.add(bs.getTile(0, BookShelf.getMAX_Column() - 1));
                validGroups.add(bs.getTile(1, BookShelf.getMAX_Column() - 2));
                validGroups.add(bs.getTile(1, BookShelf.getMAX_Column() - 1));
            }
        }
    }

    private void MidLeft(BookShelf bs) {
        if (NoItemsNull(bs, 2, 0)) {
            if ((bs.getTile(2, 0).getCategory() == bs.getTile(2, 1).getCategory()) &&
                    (bs.getTile(2, 0).getCategory() == bs.getTile(3, 0).getCategory()) &&
                    (bs.getTile(2, 0).getCategory() == bs.getTile(3, 1).getCategory())) {
                switch (bs.getTile(2, 0).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(2, 0));
                validGroups.add(bs.getTile(2, 1));
                validGroups.add(bs.getTile(3, 0));
                validGroups.add(bs.getTile(3, 1));
            }
        }
    }
    private void MidRight(BookShelf bs) {
        if (NoItemsNull(bs, 2, BookShelf.getMAX_Column() - 2)) {
            if ((bs.getTile(2, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(2, BookShelf.getMAX_Column() - 1).getCategory()) &&
                    (bs.getTile(2, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(3, BookShelf.getMAX_Column() - 2).getCategory()) &&
                    (bs.getTile(2, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(3, BookShelf.getMAX_Column() - 1).getCategory())) {
                switch (bs.getTile(2, BookShelf.getMAX_Column() - 2).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(2, BookShelf.getMAX_Column() - 2));
                validGroups.add(bs.getTile(2, BookShelf.getMAX_Column() - 1));
                validGroups.add(bs.getTile(3, BookShelf.getMAX_Column() - 2));
                validGroups.add(bs.getTile(3, BookShelf.getMAX_Column() - 1));
            }
        }
    }
    private void LowerLeft(BookShelf bs) {
        if (NoItemsNull(bs, BookShelf.getMAX_Row() - 2, 0)) {
            if ((bs.getTile(BookShelf.getMAX_Row() - 2, 0).getCategory() == bs.getTile(BookShelf.getMAX_Row() - 2, 1).getCategory()) &&
                    (bs.getTile(BookShelf.getMAX_Row() - 2, 0).getCategory() == bs.getTile(BookShelf.getMAX_Row() - 1, 0).getCategory()) &&
                    (bs.getTile(BookShelf.getMAX_Row() - 2, 0).getCategory() == bs.getTile(BookShelf.getMAX_Row() - 1, 1).getCategory())) {
                switch (bs.getTile(BookShelf.getMAX_Row() - 2, 0).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(BookShelf.getMAX_Row() - 2, 0));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row() - 2, 1));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row() - 1, 0));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row() - 1, 1));
            }
        }
    }
    private void LowerRight(BookShelf bs) {
        if (NoItemsNull(bs, BookShelf.getMAX_Row() - 2, BookShelf.getMAX_Column() - 2)) {
            if ((bs.getTile(BookShelf.getMAX_Row() - 2, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(BookShelf.getMAX_Row() - 2, BookShelf.getMAX_Column() - 1).getCategory()) &&
                    (bs.getTile(BookShelf.getMAX_Row() - 2, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(BookShelf.getMAX_Row() - 1, BookShelf.getMAX_Column() - 2).getCategory()) &&
                    (bs.getTile(BookShelf.getMAX_Row() - 2, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(BookShelf.getMAX_Row() - 1, BookShelf.getMAX_Column() - 1).getCategory())) {
                switch (bs.getTile(BookShelf.getMAX_Row() - 2, BookShelf.getMAX_Column() - 2).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(BookShelf.getMAX_Row() - 2, BookShelf.getMAX_Column() - 2));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row() - 2, BookShelf.getMAX_Column() - 1));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row() - 1, BookShelf.getMAX_Column() - 2));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row() - 1, BookShelf.getMAX_Column() - 1));
            }
        }
    }
    private void FirstRow_SecondRow1(BookShelf bs){
        if((notContain(bs,0,1,1,2)) && (NoItemsNull(bs, 0, 1))){
            if((bs.getTile(0,1).getCategory() == bs.getTile(0,2).getCategory()) &&
                    (bs.getTile(0,1).getCategory() == bs.getTile(1,1).getCategory()) &&
                    (bs.getTile(0,1).getCategory() == bs.getTile(1,2).getCategory())) {
                switch (bs.getTile(0, 1).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(0,1));
                validGroups.add(bs.getTile(0,2));
                validGroups.add(bs.getTile(1,1));
                validGroups.add(bs.getTile(1,2));
            }
        }
    }
    private void FirstRow_SecondRow2(BookShelf bs) {
        if ((notContain(bs, 0, 2, 1, BookShelf.getMAX_Column() - 2)) && (NoItemsNull(bs, 0, 2))){
            if ((bs.getTile(0, 2).getCategory() == bs.getTile(0, BookShelf.getMAX_Column() - 2).getCategory()) &&
                    (bs.getTile(0, 2).getCategory() == bs.getTile(1, 2).getCategory()) &&
                    (bs.getTile(0, 2).getCategory() == bs.getTile(1, BookShelf.getMAX_Column() - 2).getCategory())) {
                switch (bs.getTile(0, 2).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(0,2));
                validGroups.add(bs.getTile(0,BookShelf.getMAX_Column()-2));
                validGroups.add(bs.getTile(1,BookShelf.getMAX_Column()-2));
                validGroups.add(bs.getTile(1,2));
            }
        }
    }
    private void SecondRow_ThirdRow1(BookShelf bs) {
        if ((notContain(bs, 1, 0, 2, 1))&& (NoItemsNull(bs, 1, 0))) {
            if ((bs.getTile(1, 0).getCategory() == bs.getTile(1, 1).getCategory()) &&
                    (bs.getTile(1, 0).getCategory() == bs.getTile(2, 0).getCategory()) &&
                    (bs.getTile(1, 0).getCategory() == bs.getTile(2, 1).getCategory())) {
                switch (bs.getTile(1, 0).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(1,0));
                validGroups.add(bs.getTile(1,1));
                validGroups.add(bs.getTile(2,0));
                validGroups.add(bs.getTile(2,1));
            }
        }
    }
    private void SecondRow_ThirdRow2(BookShelf bs) {
        if ((notContain(bs, 1, 1, 2, 2))&& (NoItemsNull(bs, 1, 1))) {
            if ((bs.getTile(1, 1).getCategory() == bs.getTile(1, 2).getCategory()) &&
                    (bs.getTile(1, 1).getCategory() == bs.getTile(2, 1).getCategory()) &&
                    (bs.getTile(1, 1).getCategory() == bs.getTile(2, 2).getCategory())) {
                switch (bs.getTile(1, 1).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(1,1));
                validGroups.add(bs.getTile(1,2));
                validGroups.add(bs.getTile(2,1));
                validGroups.add(bs.getTile(2,2));
            }

        }
    }
    private void SecondRow_ThirdRow3(BookShelf bs){
        if ((notContain(bs, 1, 2, 2, BookShelf.getMAX_Column()-2))&& (NoItemsNull(bs, 1, 2))){
            if ((bs.getTile(1,2).getCategory() == bs.getTile(1,BookShelf.getMAX_Column()-2).getCategory()) &&
                    (bs.getTile(1,2).getCategory() == bs.getTile(2,2).getCategory()) &&
                    (bs.getTile(1,2).getCategory() == bs.getTile(2,BookShelf.getMAX_Column()-2).getCategory())){
                switch(bs.getTile(1,2).getCategory()){
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(1,2));
                validGroups.add(bs.getTile(2,2));
                validGroups.add(bs.getTile(2,BookShelf.getMAX_Column()-2));
                validGroups.add(bs.getTile(1,BookShelf.getMAX_Column()-2));
            }
        }
    }
    private void SecondRow_ThirdRow4(BookShelf bs){
        if((notContain(bs, 1, BookShelf.getMAX_Column()-2, 2, BookShelf.getMAX_Column()-1))&& (NoItemsNull(bs, 1, BookShelf.getMAX_Column()-2))) {
            if ((bs.getTile(1,BookShelf.getMAX_Column()-2).getCategory() == bs.getTile(1,BookShelf.getMAX_Column()-1).getCategory()) &&
                    (bs.getTile(1,BookShelf.getMAX_Column()-2).getCategory() == bs.getTile(2,BookShelf.getMAX_Column()-2).getCategory()) &&
                    (bs.getTile(1,BookShelf.getMAX_Column()-2).getCategory() == bs.getTile(2,BookShelf.getMAX_Column()-1).getCategory())){
                switch(bs.getTile(1,BookShelf.getMAX_Column()-2).getCategory()){
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(1,BookShelf.getMAX_Column()-2));
                validGroups.add(bs.getTile(2,BookShelf.getMAX_Column()-2));
                validGroups.add(bs.getTile(2,BookShelf.getMAX_Column()-1));
                validGroups.add(bs.getTile(1,BookShelf.getMAX_Column()-1));
            }
        }
    }
    private void ThirdRow_FourthRow1(BookShelf bs){
        if((notContain(bs,2,1,3,2))&& (NoItemsNull(bs, 2, 1))){
            if ((bs.getTile(2, 1).getCategory() == bs.getTile(2, 2).getCategory()) &&
                    (bs.getTile(2, 1).getCategory() == bs.getTile(3, 1).getCategory()) &&
                    (bs.getTile(2, 1).getCategory() == bs.getTile(3, 2).getCategory())) {
                switch (bs.getTile(2, 1).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(2,1));
                validGroups.add(bs.getTile(2,2));
                validGroups.add(bs.getTile(3,1));
                validGroups.add(bs.getTile(3,2));
            }
        }
    }
    private void ThirdRow_FourthRow2(BookShelf bs){
        if((notContain(bs,2,2,3,BookShelf.getMAX_Column()-2))&& (NoItemsNull(bs, 2, 2))){
            if ((bs.getTile(2, 2).getCategory() == bs.getTile(2, BookShelf.getMAX_Column()-2).getCategory()) &&
                    (bs.getTile(2, 2).getCategory() == bs.getTile(3, 2).getCategory()) &&
                    (bs.getTile(2, 2).getCategory() == bs.getTile(3, BookShelf.getMAX_Column()-2).getCategory())) {
                switch (bs.getTile(2, 2).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(2,2));
                validGroups.add(bs.getTile(2,BookShelf.getMAX_Column()-2));
                validGroups.add(bs.getTile(3,BookShelf.getMAX_Column()-2));
                validGroups.add(bs.getTile(3,2));
            }
        }
    }
    private void FourthRow_FifthRow1(BookShelf bs){
        if ((notContain(bs, 3, 0, BookShelf.getMAX_Row()-2, 1))&& (NoItemsNull(bs, 3, 0))) {
            if ((bs.getTile(3, 0).getCategory() == bs.getTile(3, 1).getCategory()) &&
                    (bs.getTile(3, 0).getCategory() == bs.getTile(BookShelf.getMAX_Row()-2, 0).getCategory()) &&
                    (bs.getTile(3, 0).getCategory() == bs.getTile(BookShelf.getMAX_Row()-2, 1).getCategory())) {
                switch (bs.getTile(3, 0).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(3,0));
                validGroups.add(bs.getTile(3,1));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2,0));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2,1));
            }
        }
    }
    private void FourthRow_FifthRow2(BookShelf bs){
        if ((notContain(bs, 3, 1, BookShelf.getMAX_Row()-2, 2))&& (NoItemsNull(bs, 3, 1))) {
            if ((bs.getTile(3, 1).getCategory() == bs.getTile(3, 2).getCategory()) &&
                    (bs.getTile(3, 1).getCategory() == bs.getTile(BookShelf.getMAX_Row()-2, 1).getCategory()) &&
                    (bs.getTile(3, 1).getCategory() == bs.getTile(BookShelf.getMAX_Row()-2, 2).getCategory())) {
                switch (bs.getTile(3, 1).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(3,1));
                validGroups.add(bs.getTile(3,2));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2,1));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2,2));
            }
        }
    }
    private void FourthRow_FifthRow3(BookShelf bs){
        if((notContain(bs, 3, 2, BookShelf.getMAX_Row()-2, BookShelf.getMAX_Column()-2))&&
                (NoItemsNull(bs, 3, 2))){
            if ((bs.getTile(3,2).getCategory() == bs.getTile(3,BookShelf.getMAX_Column()-2).getCategory()) &&
                    (bs.getTile(3,2).getCategory() == bs.getTile(BookShelf.getMAX_Row()-2,2).getCategory()) &&
                    (bs.getTile(3,2).getCategory() == bs.getTile(BookShelf.getMAX_Row()-2,BookShelf.getMAX_Column()-2).getCategory())){
                switch(bs.getTile(3,2).getCategory()){
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(3,2));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2,2));
                validGroups.add(bs.getTile(3,BookShelf.getMAX_Column()-2));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2,BookShelf.getMAX_Column()-2));
            }
        }
    }
    private void FourthRow_FifthRow4(BookShelf bs) {
        if((notContain(bs, 3, BookShelf.getMAX_Column() - 2, BookShelf.getMAX_Row()-2, BookShelf.getMAX_Column() - 1))&&
                (NoItemsNull(bs, 3, BookShelf.getMAX_Column()-2))){
            if ((bs.getTile(3, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(3, BookShelf.getMAX_Column() - 1).getCategory()) &&
                    (bs.getTile(3, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(BookShelf.getMAX_Row()-2, BookShelf.getMAX_Column() - 2).getCategory()) &&
                    (bs.getTile(3, BookShelf.getMAX_Column() - 2).getCategory() == bs.getTile(BookShelf.getMAX_Row()-2, BookShelf.getMAX_Column() - 1).getCategory())) {
                switch (bs.getTile(3, BookShelf.getMAX_Column() - 2).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(3, BookShelf.getMAX_Column() - 2));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2, BookShelf.getMAX_Column() - 2));
                validGroups.add(bs.getTile(3, BookShelf.getMAX_Column() - 1));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2, BookShelf.getMAX_Column() - 1));
            }
        }
    }
    private void FifthRow_SixthRow1(BookShelf bs){
        if((notContain(bs, BookShelf.getMAX_Row()-2,1,BookShelf.getMAX_Row()-1,2))&&
                (NoItemsNull(bs, BookShelf.getMAX_Row()-2, 1))){
            if ((bs.getTile(BookShelf.getMAX_Row()-2, 1).getCategory() == bs.getTile(BookShelf.getMAX_Row()-2, 2).getCategory()) &&
                    (bs.getTile(BookShelf.getMAX_Row()-2, 1).getCategory() == bs.getTile(BookShelf.getMAX_Row()-1, 1).getCategory()) &&
                    (bs.getTile(BookShelf.getMAX_Row()-2, 1).getCategory() == bs.getTile(BookShelf.getMAX_Row()-1, 2).getCategory())) {
                switch (bs.getTile(BookShelf.getMAX_Row()-2, 1).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2,1));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2,2));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-1,1));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-1,2));
            }
        }
    }
    private void FifthRow_SixthRow2(BookShelf bs ){
        if((notContain(bs,BookShelf.getMAX_Row()-2,2,BookShelf.getMAX_Row()-1,BookShelf.getMAX_Column()-2))&&
                (NoItemsNull(bs, BookShelf.getMAX_Row()-2, 2))){
            if ((bs.getTile(BookShelf.getMAX_Row()-2, 2).getCategory() == bs.getTile(BookShelf.getMAX_Row()-2, BookShelf.getMAX_Column()-2).getCategory()) &&
                    (bs.getTile(BookShelf.getMAX_Row()-2, 2).getCategory() == bs.getTile(BookShelf.getMAX_Row()-1, 2).getCategory()) &&
                    (bs.getTile(BookShelf.getMAX_Row()-2, 2).getCategory() == bs.getTile(BookShelf.getMAX_Row()-1, BookShelf.getMAX_Column()-2).getCategory())) {
                switch (bs.getTile(BookShelf.getMAX_Row()-2, 2).getCategory()) {
                    case CATS -> counterCats++;
                    case BOOKS -> counterBooks++;
                    case GAMES -> counterGames++;
                    case FRAMES -> counterFrames++;
                    case PLANTS -> counterPlants++;
                    case TROPHIES -> counterTrophies++;
                }
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2,2));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-2,BookShelf.getMAX_Column()-2));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-1,BookShelf.getMAX_Column()-2));
                validGroups.add(bs.getTile(BookShelf.getMAX_Row()-1,2));
            }
        }
    }
    private boolean TwoSquares(){
        if(getCounterBooks()>=2 || getCounterCats()>=2 || getCounterFrames()>=2 || getCounterGames()>=2 ||
                getCounterPlants()>=2 || getCounterTrophies()>=2)
            return true;
        else
            return false;
    }

    private boolean notContain(BookShelf bs,int x, int y, int z,int w){
        if((!validGroups.contains(bs.getTile(x,y))) && (!validGroups.contains(bs.getTile(x,w))) &&
                (!validGroups.contains(bs.getTile(z,y))) && (!validGroups.contains(bs.getTile(z,w)))){
            return true;
        }
        return false;
    }
    private boolean NoItemsNull(BookShelf bs,int k, int l){
        if((bs.getTile(k,l)!=null) && (bs.getTile(k,l+1)!=null) &&
                (bs.getTile(k+1,l)!=null) && (bs.getTile(k+1,l+1) != null))
            return true;
        else return false;
    }

    public void print(){
        System.out.print("TWO SQUARES. Make 2 groups each containing 4 tiles of the same type in a 2x2 square.\n" +
                "The tiles of one square can be different from those of the other square. --> You can still get" + getPoints() + "POINTS\n");
    }
}