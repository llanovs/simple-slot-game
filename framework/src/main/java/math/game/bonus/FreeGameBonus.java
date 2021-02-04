package math.game.bonus;

import math.game.enums.BonusType;

public class FreeGameBonus extends Bonus {

    private int count;
    private int index;

    public FreeGameBonus(int count) {
        this();
        this.count = count;
    }

    public FreeGameBonus() {
        super(BonusType.FREE_GAMES_BONUS);
    }

    public boolean isFinished() {
        return 0 < count && count <= index;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public void incIndex() {
        ++index;
    }

    public int getCount() {
        return count;
    }

    public int getIndex() {
        return index;
    }
}
