package math.game.engine;

import math.game.GameRound;
import math.game.bonus.Bonus;
import math.game.enums.Action;
import math.game.session.GameSession;

public abstract class SlotEngine {

    public GameRound createGameRound(GameSession session, Action action) {
        return new GameRound(session.getBet(), action);
    }

    public abstract void calculations(GameRound gameRound, GameSession session);

    public abstract void assignBonus(GameRound gameRound, GameSession session);

    public void playGameRound(GameRound gameRound, GameSession session){
        calculations(gameRound, session);
        assignBonus(gameRound, session);
        session.addTotalWin(gameRound.getWin());
    }

    public void playBonusRound(Bonus bonus, GameSession session){
        GameRound gameRound = createGameRound(session, Bonus.getActionByBonusType(bonus.getBonusType()));
        playGameRound(gameRound, session);
        if(gameRound.getAction() == Action.FREESPIN){
            bonus.incIndex();
        }
        if (bonus.isFinished()) {
            session.addTotalWin(bonus.getWin());
            session.removeBonus(bonus.getBonusType());
        }
    }

    public abstract GameSession getSession();
}
