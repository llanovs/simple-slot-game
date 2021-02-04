package com.games.simplegames;

import math.game.Main;

public class BonusGameMain extends Main {

    public static void main(String[] args) {
        BonusGameSession session = new BonusGameSession();
        BonusGameEngine engine = new BonusGameEngine(session);
        run(engine, session);
    }
}
