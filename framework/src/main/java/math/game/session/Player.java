package math.game.session;

import math.game.exception.CoreException;

public class Player {

    private long balance;

    /**
     * Constructs an empty player
     */
    public Player() {}
    /**
     * Constructs a player with given balance and currency
     */
    public Player(long balance) {
        if(balance <= 0){
            throw new CoreException(CoreException.NOT_ENOUGH_BALANCE);
        }
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    /**
     * Adds money value to player balance
     * @param balance balance to add in player currency
     */
    public void addBalance(long balance) {
        this.balance += balance;
    }

    @Override
    public String toString() {
        return "Player{" +
                "balance=" + balance +
                '}';
    }
}
