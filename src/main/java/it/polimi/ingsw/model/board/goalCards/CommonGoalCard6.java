    package it.polimi.ingsw.model.board.goalCards;

    import it.polimi.ingsw.model.player.BookShelf;
    import it.polimi.ingsw.Launcher;

    public class CommonGoalCard6 extends CommonGoalCard implements CheckCommonGoalCard {
        private final Launcher L;

        public CommonGoalCard6(Launcher L){
            this.L = L;
        }
        //METODO ALTERNATIVO STILE C
        public void checkGoal(BookShelf bs) {
            int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0;
            for (int i = 0; i < BookShelf.getMAX_Row(); i++) {
               for(int j=0;j<BookShelf.getMAX_Column();j++){
                   switch (bs.getTile(i, j).getCategory()) {
                       case CATS -> c1++;
                       case FRAMES -> c2++;
                       case BOOKS -> c3++;
                       case GAMES -> c4++;
                       case PLANTS -> c5++;
                       case TROPHIES -> c6++;
                   }
               }
            }
            if(c1>7||c2>7||c3>7||c4>7||c5>7||c6>7) increaseNumCompleted();


        }
    }

       /* private List<String> Colors=new ArrayList<>();

    //METODO CHE VISITA TILES E SEGNA QUELLE VISITATE

        @Override
      public void checkGoal(BookShelf bs) {
            setColors();
            ItemTile T = new ItemTile(Colors.get(0));
            if (SameColor(T, bs) >= 8) increaseNumCompleted();
            else {
                int i = 1;
                while (i < 5) {
                    T=new ItemTile(Colors.get(i));
                    if (SameColor(T, bs) >= 8) {
                        increaseNumCompleted();
                        i=5;
                    }
                    else i++;
                }

            }
            bs.resetVisited();
        }


       private int SameColor(ItemTile T, BookShelf bs) {
            int contatore = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!bs.getTile(i, j).isVisited() && bs.getTile(i, j).getCategory() == T.getCategory()) {
                        contatore++;
                        bs.getTile(i, j).setVisited(true);
                    }
                }
            }
            return contatore;
        }


        private void setColors(){
            Colors.add("CATS");
            Colors.add("TROPHIES");
            Colors.add("GAMES");
            Colors.add("BOOKS");
            Colors.add("FRAMES");
            Colors.add("PLANTS");
        }

    }*/