package it.polimi.ingsw.Messages.ServerToClient;

public class ScoreMessage extends ServerMessage{
    private final Integer score;
    public ScoreMessage(Integer score) {
        super(MessageCategory.SCORE);
        this.score=score;
    }

    public Integer getScore() {
        return score;
    }
}
