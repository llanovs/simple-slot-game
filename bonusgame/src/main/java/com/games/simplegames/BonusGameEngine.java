package com.games.simplegames;

import math.game.GameRound;
import math.game.RandomUtils;
import math.game.engine.SlotEngine;
import math.game.enums.Action;
import math.game.enums.BonusType;
import math.game.session.GameSession;

public class BonusGameEngine extends SlotEngine {

    private BonusGameSession session;

    public BonusGameEngine(BonusGameSession session) {
        this.session = session;
    }

    @Override
    public void calculations(GameRound gameRound, GameSession session) {
        if (gameRound.getAction() == Action.PICK) {
            BonusGamePickBonus bonus = (BonusGamePickBonus) session.getBonus(BonusType.PICK_BONUS);
            int pickIndex = bonus.getPickIndex();
            if(bonus.checkIndex(pickIndex)) {
                if (bonus.getValue(pickIndex) == BonusGame.PICK_BOX_END) {
                    bonus.setFinished(true);
                } else {
                    bonus.addWin(bonus.getValue(pickIndex));
                    bonus.addPick(pickIndex, bonus.getValue(pickIndex));
                }
            }
        }
    }

    @Override
    public void assignBonus(GameRound gameRound, GameSession session) {
        if(gameRound.getAction() == Action.SPIN) {
            boolean hasPickBonusGame = RandomUtils.getIndex(session.getRandom(), BonusGame.CHANCE_FOR_PICK) == 1;
            if (hasPickBonusGame) {
                int indexFinishGame = session.getRandom().nextInt(BonusGame.MAX_PICK_INDEX);
                int values[] = new int[5];
                for (int i = 0; i < BonusGame.MAX_PICK_INDEX; i++) {
                    values[i] = i == indexFinishGame ? BonusGame.PICK_BOX_END : BonusGame.PICK_BOX_WIN;
                }
                session.putBonus(new BonusGamePickBonus(values, BonusGame.MAX_PICK_INDEX));
            }
        }
    }

    public BonusGameSession getSession() {
        return session;
    }
}
