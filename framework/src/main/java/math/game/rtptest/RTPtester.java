package math.game.rtptest;

import math.game.GameRound;
import math.game.engine.SlotEngine;
import math.game.enums.Action;
import math.game.enums.BonusType;
import math.game.session.GameSession;
import math.game.session.Player;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class RTPtester<E extends SlotEngine, S extends GameSession> {

    public static final BigDecimal DECIMAL_100 = BigDecimal.valueOf(100);

    public static long SPIN_COUNT = 1000000000;
    public static final long BALANCE = 1000000000;
    public static int BET = 10;

    public static final String TOTAL_BET = "total_bet";
    public static final String TOTAL_WIN = "total_win";
    public static final String BASE_GAME_WIN = "base_game_win";
    public static final String FREE_GAME_WIN = "free_game_win";
    public static final String PICK_GAME_WIN = "pick_game_win";
    public static final String TOTAL_ROUNDS = "total_rounds";

    protected final E engine;
    protected final Map<String, BigInteger> accumulatorMap = new HashMap<>();

    protected S session;

    public RTPtester(E slotEngine, S session) {
        this.engine = slotEngine;
        this.session = session;
    }

    public void runTest(int stake, long numberOfIterations) {
        SPIN_COUNT = numberOfIterations;
        BET = stake;
        runTest();
    }

    public void runTest() {
        long startTime = System.currentTimeMillis();
        accumulatorMap.clear();
        session.setPlayer(new Player(BALANCE));
        session.setBet(BET);

        for (int i = 0; i < SPIN_COUNT; i++) {
            session.setTotalWin(0);
            GameRound gameRound = engine.createGameRound(session, Action.SPIN);
            engine.playGameRound(gameRound, session);
            inc(TOTAL_ROUNDS);
            add(BASE_GAME_WIN, session.getTotalWin());
            long win = session.getTotalWin();
            while (session.getBonuses().size() > 0) {
                if(session.getBonus(BonusType.FREE_GAMES_BONUS) != null) {
                    engine.playBonusRound(session.getBonus(BonusType.FREE_GAMES_BONUS), session);
                    if(session.getBonus(BonusType.FREE_GAMES_BONUS) == null) {
                        add(FREE_GAME_WIN, session.getTotalWin() - win);
                    }
                } else if(session.getBonus(BonusType.PICK_BONUS) != null){
                    engine.playBonusRound(session.getBonus(BonusType.PICK_BONUS), session);
                    if(session.getBonus(BonusType.PICK_BONUS) == null) {
                        add(PICK_GAME_WIN, session.getTotalWin() - win);
                    }
                }
                inc(TOTAL_ROUNDS);
            }

            add(TOTAL_BET, session.getBet());
            add(TOTAL_WIN, session.getTotalWin());

            if ((i + 1) % (SPIN_COUNT / 20) == 0) {
                BigDecimal rtp = new BigDecimal(get(TOTAL_WIN))
                        .multiply(DECIMAL_100)
                        .divide(new BigDecimal(get(TOTAL_BET)), 3, RoundingMode.HALF_DOWN);

                long time = (System.currentTimeMillis() - startTime) / 1000;

                System.out.printf("%,d spins finished, RTP=%s, time=%02d:%02d:%02d\n",
                        i + 1, rtp + "%", time / 3600, (time % 3600) / 60, time % 60);
            }
        }
        printResult(startTime);
    }

    private void printResult(long startTime) {
        System.out.println("Number of iterations: " + SPIN_COUNT);
        System.out.println("Number of total rounds: " + get(TOTAL_ROUNDS));
        System.out.println("Total bet amount: " + get(TOTAL_BET));
        System.out.println("Total win amount: " + get(TOTAL_WIN));

        printRtp("Base game ", BASE_GAME_WIN);

        System.out.println("Bonuses: ");
        if(!get(FREE_GAME_WIN).equals(BigInteger.ZERO)) {
            printRtp("Free game ", FREE_GAME_WIN);
        }
        if(!get(PICK_GAME_WIN).equals(BigInteger.ZERO)) {
            printRtp("Pick game ", PICK_GAME_WIN);
        }

        printRtp("Final ", TOTAL_WIN);
        long time = (System.currentTimeMillis() - startTime) / 1000;
        System.out.print("Time elapsed=");
        System.out.printf("%02d:%02d:%02d\n", time / 3600, (time % 3600) / 60, time % 60);
    }

    private void printRtp(String description, String key) {
        System.out.println(description);
        System.out.println("Win amount: " + get(key));
        BigDecimal rtp = new BigDecimal(get(key))
                .multiply(DECIMAL_100)
                .divide(new BigDecimal(get(TOTAL_BET)), 2, RoundingMode.HALF_DOWN);
        System.out.println(description + "RTP = " + rtp + "%");
        System.out.println();
    }

    protected void add(String key, long value) {
        BigInteger i = BigInteger.valueOf(value);
        add(key, i);
    }

    protected void add(String key, BigInteger value) {
        accumulatorMap.compute(key, (k, v) -> (v == null) ? value : v.add(value));
    }

    protected BigInteger get(String key) {
        return accumulatorMap.getOrDefault(key, BigInteger.ZERO);
    }

    protected void inc(String key) {
        add(key, 1);
    }
}
