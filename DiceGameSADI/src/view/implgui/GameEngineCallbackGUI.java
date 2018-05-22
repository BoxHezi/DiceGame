package view.implgui;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.DicePanel;
import view.GameDetailPanel;
import view.MainFrame;
import view.implgui.interfaces.GameEngineCallback;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private MainFrame mainFrame;

    public GameEngineCallbackGUI(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine) {
        rolling(dicePair);
    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine) {
        displayResult(result);
    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
        rolling(dicePair);
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine) {
        displayResult(result);
        recordGameProgress(result, gameEngine);
        resetPlayerBetAmount((ArrayList<Player>) gameEngine.getAllPlayers());

        // set place bet button to be able to click to let player place bet
        mainFrame.getToolBar().getPlaceBetButton().setEnabled(true);
    }

    /**
     * update dice pair while rolling
     *
     * @param dicePair dice pair
     */
    private void rolling(DicePair dicePair) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DicePanel dicePanel = (DicePanel) mainFrame.getMainPanel().getRightComponent();
                dicePanel.updateDicePanelInfo(dicePair);
            }
        });
    }

    /**
     * update dice image after rolling finished
     *
     * @param result dice pair result
     */
    private void displayResult(DicePair result) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DicePanel dicePanel = (DicePanel) mainFrame.getMainPanel().getRightComponent();

                // disable place bet and roll button to avoid one player place bet or roll dice twice
                // in one round
                mainFrame.getToolBar().getPlaceBetButton().setEnabled(false);
                mainFrame.getToolBar().getRollButton().setEnabled(false);

                dicePanel.updateDicePanelInfo(result);
            }
        });
    }

    /**
     * displaye game result detail in the text area
     *
     * @param houseResult house result
     */
    private void recordGameProgress(DicePair houseResult, GameEngine gameEngine) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<Player> players = (ArrayList<Player>) mainFrame.getGameEngine().getAllPlayers();
                GameDetailPanel gameDetailPanel = (GameDetailPanel) mainFrame.getMainPanel().getLeftComponent();
                JTextArea textArea = gameDetailPanel.getTextArea();
                for (Player player : players) {
                    int playerRollResult = player.getRollResult().getDice1() + player.getRollResult().getDice2();
                    textArea.append(String.format("Name: %s Result: %d\n", player.toString(), playerRollResult));
                }
                textArea.append(String.format("House Result: %d\n", houseResult.getDice1() + houseResult.getDice2()));

                Player winner = getWinner((List<Player>) gameEngine.getAllPlayers(), houseResult);
                if (null != winner) {
                    textArea.append(String.format("The winner is: %s\n", winner.getPlayerName()));
                } else {
                    textArea.append("The winner is the house!\n");
                }

                //update player's info in status
                Player selectedPlayer = (Player) gameDetailPanel.getPlayerList().getSelectedValue();
                mainFrame.getStatusBar().displayPlayerInfo(selectedPlayer);
            }
        });
    }

    /**
     * a method to reset all player's bet amount to let player enter bet amount again
     *
     * @param players player to reset bet amount
     */
    private void resetPlayerBetAmount(ArrayList<Player> players) {
        for (Player player : players) {
            player.placeBet(0);
        }
    }

    /**
     * method to get winner in order to display to the textarea for game progress
     *
     * @param players     all the players
     * @param houseResult house result
     * @return one player is there is a winner, null if the house is the winner
     */
    private Player getWinner(List<Player> players, DicePair houseResult) {
        Player winner = null;
        int winnerResult = 0;

        for (Player player : players) {
            int playerResult = player.getRollResult().getDice1() + player.getRollResult().getDice2();
            if (playerResult > (houseResult.getDice1() + houseResult.getDice2())) {
                if (playerResult > winnerResult) {
                    winner = player;
                    winnerResult = playerResult;
                }
            }
        }

        if (null != winner) {
            return winner;
        }

        return null;
    }
}
