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
        rolling(dicePair, false);
    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine) {
        GameDetailPanel gameDetailPanel = getGameDetailPanel();
        gameDetailPanel.updateRollStatus(player, true);
        displayResult(result, false);
        gameDetailPanel.updateRollingStatus(player, false);
    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
        rolling(dicePair, true);
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine) {
        displayResult(result, true);
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
    private void rolling(DicePair dicePair, boolean isHouse) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DicePanel dicePanel = mainFrame.getMainPanel().getRightComponent();
                if (!isHouse) {
                    GameDetailPanel gameDetailPanel = getGameDetailPanel();
                    Player player = getSelectedPlayer();

                    boolean rolling = true;
                    boolean rolled = false;
                    if (null != player) {
                        rolling = gameDetailPanel.getPlayerRollingMap().get(player);
                        rolled = gameDetailPanel.getPlayerRollStatusMap().get(player);
                    }
                    if (rolled) {
                        dicePanel.setDiceImg(player);
                    } else {
                        if (rolling) {
                            dicePanel.updateDicePanelInfo(dicePair);
                        } else {
                            dicePanel.updateDicePanelInfo(null);
                        }
                    }
                } else {
                    dicePanel.updateDicePanelInfo(dicePair);
                }
            }
        });
    }

    /**
     * update dice image after rolling finished
     *
     * @param result dice pair result
     */
    private void displayResult(DicePair result, boolean isHouse) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DicePanel dicePanel = mainFrame.getMainPanel().getRightComponent();
                if (!isHouse) {
                    GameDetailPanel gameDetailPanel = getGameDetailPanel();
                    Player player = getSelectedPlayer();

                    boolean rolling = true;
                    boolean rolled = false;
                    if (null != player) {
                        rolling = gameDetailPanel.getPlayerRollingMap().get(player);
                        rolled = gameDetailPanel.getPlayerRollStatusMap().get(player);
                    }
                    if (rolled) {
                        dicePanel.setDiceImg(player);
                    } else {
                        if (rolling) {
                            // disable place bet and roll button to avoid one player place bet or roll dice twice
                            // in one round
                            mainFrame.getToolBar().getPlaceBetButton().setEnabled(false);
                            mainFrame.getToolBar().getRollButton().setEnabled(false);
                            dicePanel.updateDicePanelInfo(result);
                        }
                        if (!rolling) {
                            dicePanel.updateDicePanelInfo(null);
                        }
                    }
                } else {
                    dicePanel.updateDicePanelInfo(result);
                }
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
                GameDetailPanel gameDetailPanel = getGameDetailPanel();
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

    /**
     * method to get selected player
     *
     * @return selected player
     */
    private Player getSelectedPlayer() {
        GameDetailPanel gameDetailPanel = mainFrame.getMainPanel().getLeftComponent();
        JList players = gameDetailPanel.getPlayerList();
        return (Player) players.getSelectedValue();
    }

    /**
     * method to get game detail panel
     *
     * @return game detail panel
     */
    private GameDetailPanel getGameDetailPanel() {
        return mainFrame.getMainPanel().getLeftComponent();
    }
}
