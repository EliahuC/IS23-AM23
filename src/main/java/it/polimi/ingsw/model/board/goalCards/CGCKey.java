package it.polimi.ingsw.model.board.goalCards;

/**
 * Class that rapresent the pair < CommonGoalCard , Id >
 * @author Eliahu Cohen
 *
 */
public class CGCKey {
    private CommonGoalCard commonGoalCard;
    private Integer id;

    /**
     * @author Eliahu Cohen
     * @param commonGoalCard received
     * @param id of the card
     */
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
