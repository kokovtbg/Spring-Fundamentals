package bg.softuni.battleships.model.dto;

public class StartBattleDTO {
    private long attackerId;
    private long defenderId;

    public long getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(long attackerId) {
        this.attackerId = attackerId;
    }

    public long getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(long defenderId) {
        this.defenderId = defenderId;
    }
}
