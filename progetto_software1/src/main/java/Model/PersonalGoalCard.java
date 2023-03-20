package Model;

public class PersonalGoalCard {
    private final BookShelf GoalBookshelf;
    private final int id;

       public PersonalGoalCard(int id)  {
           this.id=id;
           this.GoalBookshelf =null;

       }

       public boolean CheckGoal(BookShelf playerBS){
           return true;
       }
}
