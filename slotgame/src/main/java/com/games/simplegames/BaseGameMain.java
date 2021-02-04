package com.games.simplegames;

import math.game.Main;

public class BaseGameMain {

    public static void main(String[] args) {
        SlotGameSession session = new SlotGameSession();
        SlotGameEngine engine = new SlotGameEngine(session);
        new Main().run(engine, session);
    }
}
