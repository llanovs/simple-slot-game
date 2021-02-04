package com.games.simplegames.rtp;

import com.games.simplegames.SlotGameEngine;
import com.games.simplegames.SlotGameSession;
import math.game.rtptest.RTPtester;
import math.game.rtptest.GameInfo;

public class SlotGameRtpTester extends RTPtester<SlotGameEngine, SlotGameSession> implements GameInfo {

    static SlotGameEngine engine = new SlotGameEngine(new SlotGameSession());

    public SlotGameRtpTester() {
        super(engine, engine.getSession());
    }

    public static String getDisplayName() {
        return "Slot game";
    }

    public static void main(String[] args) {
        String gameName = getDisplayName();
        System.out.println(String.format("GAME: %s, SPIN_COUNT=%,d, BET=%s", gameName, SPIN_COUNT, BET));
        new RTPtester(engine, engine.getSession()).runTest();
    }
}
