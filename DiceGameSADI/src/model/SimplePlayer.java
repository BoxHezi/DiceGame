package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player {
    private String id;
    private String name;
    private int points;

    private int bet;
    private DicePair rollResult;

    public SimplePlayer(String id, String name, int points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public void setPlayerName(String name) {
        this.name = name;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String getPlayerId() {
        return id;
    }

    @Override
    public boolean placeBet(int bet) {
        this.bet = bet;
        if (this.getPoints() < bet) {
            return false;
        }
        this.setPoints(this.getPoints() - bet);
        return true;
    }

    @Override
    public int getBet() {
        return bet;
    }

    @Override
    public DicePair getRollResult() {
        return rollResult;
    }

    @Override
    public void setRollResult(DicePair rollResult) {
        this.rollResult = rollResult;
    }

    @Override
    public String toString() {
        return getPlayerName();
    }
}
