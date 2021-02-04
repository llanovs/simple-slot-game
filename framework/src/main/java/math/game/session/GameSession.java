package math.game.session;

import math.game.bonus.Bonus;
import math.game.enums.Action;
import math.game.enums.BonusType;
import math.game.exception.CoreException;

import java.util.Collection;
import java.util.Map;
import java.util.Random;


public class GameSession {

    private int bet;
    private Player player;
    private Map<BonusType, Bonus> bonusMap;
    private Random random;
    private long totalWin;

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        if(bet < 0){
            throw new CoreException(CoreException.INCORECT_BET);
        }
        this.bet = bet;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Random getRandom() {
        return random;
    }

    public void setBonusMap(Map<BonusType, Bonus> bonusMap) {
        this.bonusMap = bonusMap;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public long getTotalWin() {
        return totalWin;
    }

    public void addTotalWin(long totalWin) {
        this.totalWin += totalWin;
    }

    public void setTotalWin(long totalWin) {
        this.totalWin = totalWin;
    }

    public void putBonus(Bonus bonus) {
        bonusMap.put(bonus.getBonusType(), bonus);
    }

    public Map<BonusType, Bonus> getBonusMap() {
        return bonusMap;
    }

    public Bonus getBonus(BonusType bonusType) {
        for (Bonus bonus : getBonuses()) {
            if (bonus.getBonusType() == bonusType) {
                return bonus;
            }
        }
        return null;
    }

    public void removeBonus(BonusType bonusType) {
        bonusMap.remove(bonusType);
    }

    public Collection<Bonus> getBonuses() {
        return bonusMap.values();
    }

    public Action nextAction() {
        if (bonusMap.size() == 0)
            return Action.SPIN;
        else if (getBonus(BonusType.FREE_GAMES_BONUS) != null)
            return Action.FREESPIN;
        else
            return Action.PICK;
    }
}
