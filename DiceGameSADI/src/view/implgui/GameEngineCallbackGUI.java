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
        recordGameProgress(result);
        resetPlayerBetAmount((ArrayList<Player>) gameEngine.getAllPlayers());
    }

    /**
     *  update dice pair while rolling
     * @param dicePair dice pair
     */
    private void rolling(DicePair dicePair) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DicePanel dicePanel = (DicePanel) mainFrame.getMainPanel().getRightComponent();
                int totalDiceValue = dicePair.getDice1() + dicePair.getDice2();
                dicePanel.getTotalValue().setText(String.valueOf(totalDiceValue));

                dicePanel.updateDiceImg(dicePair);
            }
        });
    }

    /**
     *  update dice image after rolling finished
     * @param result dice pair result
     */
    private void displayResult(DicePair result) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DicePanel dicePanel = (DicePanel) mainFrame.getMainPanel().getRightComponent();
                int totalDiceValue = result.getDice1() + result.getDice2();
                dicePanel.getTotalValue().setText(String.valueOf(totalDiceValue));

                mainFrame.getToolBar().getPlaceBetButton().setEnabled(true);
                mainFrame.getToolBar().getRollButton().setEnabled(false);

                dicePanel.updateDiceImg(result);
            }
        });
    }

    /**
     *  displaye game result detail in the text area
     * @param houseResult house result
     */
    private void recordGameProgress(DicePair houseResult) {
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

                Player selectedPlayer = (Player) gameDetailPanel.getPlayerList().getSelectedValue();
                mainFrame.getStatusBar().displayPlayerInfo(selectedPlayer);
            }
        });
    }

    /**
     *  a method to reset all player's bet amount to let player enter bet amount again
     * @param players player to reset bet amount
     */
    private void resetPlayerBetAmount(ArrayList<Player> players) {
        for (Player player : players) {
            player.placeBet(0);
        }
    }
}
