package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameDetailPanel;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ToolBarController extends JOptionPane implements ActionListener {
    private GameEngine gameEngine;
    private MainFrame mainFrame;

    public ToolBarController(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase(mainFrame.getToolBar().getRollCommand())) {
            rollDice(true);
        } else if (event.getActionCommand().equalsIgnoreCase(mainFrame.getToolBar().getPlaceBetCommand())) {
            placeBet();
        } else if (event.getActionCommand().equalsIgnoreCase(mainFrame.getToolBar().getHouseRollCommand())) {
            rollDice(false);
        }
    }

    /**
     * place bet, create new thread when invoke method in GameEngineImpl
     */
    private void placeBet() {
        //get player select
        Player player = getSelectedPlayer();
        boolean validate = validationCheck(player);

        if (validate) {
            //get bet amount placed
            String pointStr;
            int point = 0;
            try {
                pointStr = String.valueOf(mainFrame.getToolBar().getBetAmountText().getText());
                point = Integer.valueOf(pointStr);
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Illegal amount, try again!");
            }

            int finalPoint = point;
            new Thread() {
                @Override
                public void run() {
                    boolean enough = gameEngine.placeBet(player, finalPoint);
                    if (!enough) {
                        showMessageDialog(null, "Not enough point!");
                    } else if (finalPoint > 0) {
                        showMessageDialog(null, "Bet placed!");
                        mainFrame.getStatusBar().getPointStatus().setText(String.valueOf(player.getPoints()));

                        //update button status
                        mainFrame.getToolBar().getPlaceBetButton().setEnabled(false);
                        mainFrame.getToolBar().getRollButton().setEnabled(true);

                        //reset text area for bet amount to nothing
                        mainFrame.getToolBar().getBetAmountText().setText("");
                    }
                }
            }.start();
        }
    }

    /**
     * roll dice for both player and house
     *
     * @param isPlayer true if it is player round
     *                 false if it is house rolling
     */
    private void rollDice(boolean isPlayer) {
        new Thread() {
            @Override
            public void run() {
                GameDetailPanel gameDetailPanel = (GameDetailPanel) mainFrame.getMainPanel().getLeftComponent();
                Player player = getSelectedPlayer();
                if (isPlayer) {
                    gameDetailPanel.updateRollStatus(player, true);

                    //disable roll button to avoid the same player click it twice
                    mainFrame.getToolBar().getRollButton().setEnabled(false);
                    gameEngine.rollPlayer(player, 0, 100, 20);

                    //enable house roll button after at least one player roll
                    mainFrame.getToolBar().getHouseRollButton().setEnabled(canHouseRoll());
                } else {
                    gameEngine.rollHouse(0, 100, 20);

                    //disable house roll button to avoid unwanted behaviors
                    mainFrame.getToolBar().getHouseRollButton().setEnabled(false);
                    //reset player roll status back to false to start a new round
                    gameDetailPanel.resetRollStatus(gameEngine);
                }
            }
        }.start();
    }

    /**
     * method to check if there is player selected
     *
     * @param selectedPlayer selected player from JList
     * @return true if there is a player selected
     */
    private boolean validationCheck(Player selectedPlayer) {
        if (null == selectedPlayer) {
            showMessageDialog(null, "Please select a player");
            return false;
        }
        return true;
    }

    /**
     * get selected player from JList
     *
     * @return player selected
     */
    private Player getSelectedPlayer() {
        GameDetailPanel gameDetailPanel = (GameDetailPanel) mainFrame.getMainPanel().getLeftComponent();
        JList playerList = gameDetailPanel.getPlayerList();
        return (Player) playerList.getSelectedValue();
    }

    /**
     * method to check if the house can roll
     *
     * @return true if every player has rolled
     */
    private boolean canHouseRoll() {
        GameDetailPanel gameDetailPanel = (GameDetailPanel) mainFrame.getMainPanel().getLeftComponent();
        HashMap<Player, Boolean> playerRollStatus = gameDetailPanel.getPlayerRollMap();
        for (Map.Entry<Player, Boolean> rolled : playerRollStatus.entrySet()) {
            if (!rolled.getValue()) {
                return false;
            }
        }
        return true;
    }
}
