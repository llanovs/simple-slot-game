package com.games.simplegames;

import math.game.bonus.Bonus;
import math.game.enums.BonusType;
import math.game.session.GameSession;
import math.game.session.Player;

import java.util.HashMap;
import java.util.Random;

public class SlotGameSession extends GameSession {

    public SlotGameSession() {
        super();
        this.setBet(SlotGame.DEFAULT_BET);
        this.setPlayer(new Player(SlotGame.BALANCE));
        this.setBonusMap(new HashMap<BonusType, Bonus>());
        this.setRandom(new Random());
        this.setTotalWin(0);
    }
}
