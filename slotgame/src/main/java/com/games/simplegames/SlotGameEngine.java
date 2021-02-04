package com.games.simplegames;

import math.game.GameRound;
import math.game.RandomUtils;
import math.game.bonus.FreeGameBonus;
import math.game.engine.SlotEngine;
import math.game.enums.Action;
import math.game.enums.BonusType;
import math.game.session.GameSession;

public class SlotGameEngine extends SlotEngine {

    private SlotGameSession session;

    public SlotGameEngine(SlotGameSession session) {
        this.session = session;
    }

    @Override
    public void calculations(GameRound gameRound, GameSession session) {
        boolean hasWin = RandomUtils.getIndex(session.getRandom(), SlotGame.CHANCE_FOR_WIN) == 1;
        gameRound.setWin(hasWin ? SlotGame.SPIN_WIN : 0);
    }

    @Override
    public void assignBonus(GameRound gameRound, GameSession session) {
        boolean hasFreeRound = RandomUtils.getIndex(session.getRandom(), SlotGame.CHANCE_FOR_FREESPIN) == 1;
        if (hasFreeRound) {
            if (gameRound.getAction() == Action.FREESPIN) {
                FreeGameBonus bonus = (FreeGameBonus) session.getBonus(BonusType.FREE_GAMES_BONUS);
                bonus.addCount(SlotGame.FREE_ROUND);
            } else {
                session.putBonus(new FreeGameBonus(SlotGame.FREE_ROUND));
            }
        }
    }

    public SlotGameSession getSession() {
        return session;
    }
}
