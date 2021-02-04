package com.games.simplegames.rtp;

import com.games.simplegames.BonusGameEngine;
import com.games.simplegames.BonusGameSession;
import math.game.rtptest.RTPtester;
import math.game.rtptest.GameInfo;

public class BonusGameRtpTester extends RTPtester<BonusGameEngine, BonusGameSession> implements GameInfo {

    static BonusGameEngine engine = new BonusGameEngine(new BonusGameSession());

    public BonusGameRtpTester() {
        super(engine, engine.getSession());
    }

    public static String getDisplayName() {
        return "Bonus game";
    }

    public static void main(String[] args) {
        String gameName = getDisplayName();
        System.out.println(String.format("GAME: %s, SPIN_COUNT=%,d, BET=%s", gameName, SPIN_COUNT, BET));
        new RTPtester(engine, engine.getSession()).runTest();
    }
}
