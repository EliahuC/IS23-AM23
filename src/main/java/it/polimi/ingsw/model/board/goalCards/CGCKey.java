package it.polimi.ingsw.model.board.goalCards;

public class CGCKey {
    private CommonGoalCard commonGoalCard;
    private Integer id;

    public CGCKey(CommonGoalCard commonGoalCard,Integer id) {
        this.commonGoalCard = commonGoalCard;
        this.id=id;
    }

    public CommonGoalCard getCommonGoalCard() {
        return commonGoalCard;
    }

    public void setCommonGoalCard(CommonGoalCard commonGoalCard) {
        this.commonGoalCard = commonGoalCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
