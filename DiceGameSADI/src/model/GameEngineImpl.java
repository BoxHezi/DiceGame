package model;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.implgui.interfaces.GameEngineCallback;

import java.util.ArrayList;
import java.util.Collection;

public class GameEngineImpl implements GameEngine {
    private ArrayList<Player> playerList = new ArrayList<>();
    private ArrayList<GameEngineCallback> callbackList = new ArrayList<>();

    @Override
    public boolean placeBet(Player player, int bet) {
        return player.placeBet(bet);
    }

    @Override
    public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
        for (GameEngineCallback gameEngineCallback : callbackList) {
            for (int delay = initialDelay; delay < finalDelay; delay += delayIncrement) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DicePairImpl tempDicePair = new DicePairImpl(rollDice(), rollDice(), GameEngine.NUM_FACES);
                gameEngineCallback.intermediateResult(player, tempDicePair, this);
            }
            DicePairImpl diceResult = new DicePairImpl(rollDice(), rollDice(), GameEngine.NUM_FACES);
            player.setRollResult(diceResult);
            gameEngineCallback.result(player, diceResult, this);
        }
    }

    @Override
    public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
        for (GameEngineCallback gameEngineCallback : callbackList) {
            for (int delay = initialDelay; delay < finalDelay; delay += delayIncrement) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DicePairImpl tempDicePair = new DicePairImpl(rollDice(), rollDice(), GameEngine.NUM_FACES);
                gameEngineCallback.intermediateHouseResult(tempDicePair, this);
            }
            DicePairImpl diceResult = new DicePairImpl(rollDice(), rollDice(), GameEngine.NUM_FACES);
            compareResult(diceResult.getDice1() + diceResult.getDice2());
            gameEngineCallback.houseResult(diceResult, this);
        }
    }

    @Override
    public void addPlayer(Player player) {
        playerList.add(player);
    }

    @Override
    public Player getPlayer(String id) {
        for (Player player : playerList) {
            if (player.getPlayerId().equals(id)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public boolean removePlayer(Player player) {
        if (playerList.contains(player)) {
            playerList.remove(player);
            return true;
        }
        return false;
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
        callbackList.add(gameEngineCallback);
    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        if (callbackList.contains(gameEngineCallback)) {
            callbackList.remove(gameEngineCallback);
            return true;
        }
        return false;
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return new ArrayList<>(playerList);
    }

    private int rollDice() {
        return (int) ((Math.random() * 5) + 1);
    }

    private void compareResult(int houseResult) {
        ArrayList<Player> players = (ArrayList<Player>) getAllPlayers();
        Player winner = null;
        int winnerResult = 0;

        for (Player player : players) {
            int playerDice1 = player.getRollResult().getDice1();
            int playerDice2 = player.getRollResult().getDice2();
            int playerResult = playerDice1 + playerDice2;
            if (playerResult == houseResult) {
                player.setPoints(player.getPoints() + player.getBet());
            } else if (playerResult > houseResult) {
                player.setPoints(player.getPoints() + player.getBet());
                if (playerResult > winnerResult) {
                    winnerResult = playerResult;
                    winner = player;
                }
            }
        }
        if (null != winner) {
            winner.setPoints(winner.getPoints() + winner.getBet());
        }
    }
}