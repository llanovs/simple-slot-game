package math.game.bonus;

import math.game.enums.Action;
import math.game.enums.BonusType;

public abstract class Bonus {

    private final BonusType bonusType;

    private long win;

    public Bonus(BonusType bonusType) {
        this.bonusType = bonusType;
    }

    public long getWin() {
        return win;
    }

    public void addWin(long win) {
        this.win += win;
    }

    public abstract boolean isFinished();

    public BonusType getBonusType() {
        return bonusType;
    }

    public static BonusType getBonusTypeByAction(Action action){
        return action == Action.FREESPIN ? BonusType.FREE_GAMES_BONUS :
                action == Action.PICK ? BonusType.PICK_BONUS : BonusType.NO_BONUS;
    }

    public static Action getActionByBonusType(BonusType bonusType){
        return bonusType == BonusType.FREE_GAMES_BONUS ? Action.FREESPIN :
                bonusType == BonusType.PICK_BONUS ? Action.PICK  : Action.SPIN;
    }

    public abstract void incIndex();
}
