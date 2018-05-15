package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 *
 * @author Caspar Ryan
 * @see model.interfaces.GameEngineCallback
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
    private Logger logger = Logger.getLogger("assignment1");

    public GameEngineCallbackImpl() {
        // FINE shows rolling output, INFO only shows result
        logger.setLevel(Level.FINE);

        // set ConsoleHandler in order to output Level.FINE to console
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.FINE);
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);
    }

    @Override
    public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine) {
        // TO DO: complete this method to log results
        logger.log(Level.FINE, player.toString() + ": ROLLING" + dicePair.toString());
    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine) {
        // TO DO: complete this method to log results
        logger.log(Level.INFO, player.toString() + ": *RESULT*" + result.toString());
    }

    // TO DO: complete the GameEngineCallback interface implementation
    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
        logger.log(Level.FINE, "House: ROLLING" + dicePair.toString());
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine) {
        logger.log(Level.INFO, "House: *RESULT*" + result.toString());
        displayResult(gameEngine);
    }

    /**
     * print out final result update to console
     *
     * @param gameEngine gameEngine to retrieve players
     */
    private void displayResult(GameEngine gameEngine) {
        ArrayList<Player> players = (ArrayList<Player>) gameEngine.getAllPlayers();
        for (Player player : players) {
            logger.log(Level.INFO, player.toString() + String.format(" Player ID: %s, points=%d",
                    player.getPlayerId(), player.getPoints()));
        }
    }
}
