package com.games.simplegames;

import math.game.bonus.PickBonus;
import math.game.bonus.PickIndex;
import math.game.exception.CoreException;

public class BonusGamePickBonus extends PickBonus implements PickIndex {

    private int pickIndex;

    public BonusGamePickBonus(int[] values, int size) {
        super(values, size);
    }

    @Override
    public int getPickIndex() {
        return this.pickIndex;
    }

    @Override
    public void setPickIndex(int pickIndex) {
        this.pickIndex = pickIndex;
    }

    @Override
    public boolean checkIndex(int pickIndex) {
        if (pickIndex  < 0 || pickIndex >= BonusGame.MAX_PICK_INDEX) {
            throw new CoreException(CoreException.INCORECT_PICK_INDEX);
        }
        return true;
    }
}
