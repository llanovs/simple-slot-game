package math.game;

import math.game.engine.SlotEngine;
import math.game.enums.Action;
import math.game.enums.BonusType;
import math.game.session.GameSession;

public class Main {
    public static final long SPIN_COUNT = 1000000;

    public static void run(SlotEngine engine, GameSession session) {
        for (int i = 0; i < SPIN_COUNT; i++){
            GameRound gameRound = engine.createGameRound(session, Action.SPIN);
            engine.playGameRound(gameRound, session);
            while (session.getBonuses().size() > 0) {
                if (session.getBonus(BonusType.FREE_GAMES_BONUS) != null) {
                    engine.playBonusRound(session.getBonus(BonusType.FREE_GAMES_BONUS), session);
                } else if (session.getBonus(BonusType.PICK_BONUS) != null) {
                    engine.playBonusRound(session.getBonus(BonusType.PICK_BONUS), session);
                }
            }
            System.out.println("Total Win: " + session.getTotalWin());
        }
        System.out.printf("RTP = %s", (double)session.getTotalWin()* 100/(SPIN_COUNT * session.getBet()));
    }
}
