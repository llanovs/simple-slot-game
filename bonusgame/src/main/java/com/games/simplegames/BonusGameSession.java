package com.games.simplegames;

import math.game.bonus.Bonus;
import math.game.enums.BonusType;
import math.game.session.GameSession;
import math.game.session.Player;

import java.util.HashMap;
import java.util.Random;

public class BonusGameSession extends GameSession {

    public BonusGameSession() {
        super();
        this.setBet(BonusGame.DEFAULT_BET);
        this.setPlayer(new Player(BonusGame.BALANCE));
        this.setBonusMap(new HashMap<BonusType, Bonus>());
        this.setRandom(new Random());
        this.setTotalWin(0);
    }
}
