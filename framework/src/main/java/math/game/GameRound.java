package math.game;

import math.game.enums.Action;
import math.game.exception.CoreException;

public class GameRound {

    private final Action action;
    private final int bet;
    private int win;

    public GameRound(int bet, Action action) {
        if(!(action instanceof Action)){
            throw new CoreException(CoreException.INVALID_ACTION);
        }

        this.bet = action == Action.SPIN ? bet : 0;
        this.action = action;
    }

    public int getBet() {
        return bet;
    }

    public Action getAction() {
        return action;
    }

    public long getWin() {
        return this.win;
    }

    public void setWin(int win) {
        this.win = win;
    }
}
