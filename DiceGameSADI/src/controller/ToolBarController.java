package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameDetailPanel;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarController extends JOptionPane implements ActionListener{
    private GameEngine gameEngine;
    private MainFrame mainFrame;

    public ToolBarController(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase(mainFrame.getToolBar().getRollCommand())) {
            //call roll dice method
            Player player = getSelectedPlayer();
            System.out.println(player.getBet());

        } else if (event.getActionCommand().equalsIgnoreCase(mainFrame.getToolBar().getPlaceBetCommand())) {
            placeBet();
        }
    }

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

            int validaPoint = point;
            new Thread() {
                public void run() {
                    if (!gameEngine.placeBet(player, validaPoint)) {
                        showMessageDialog(null, "Not enough point!");
                    } else {
                        showMessageDialog(null, "Bet placed!");
                        mainFrame.getStatusBar().getRight().setText(String.valueOf(player.getPoints()));
                        mainFrame.getToolBar().getPlaceBetButton().setEnabled(false);
                        mainFrame.getToolBar().getRollButton().setEnabled(true);
                    }
                }
            }.start();
        }
    }

    private boolean validationCheck(Player selectedPlayer) {
        if (null == selectedPlayer) {
            showMessageDialog(null, "Please select a player");
            return false;
        }
        return true;
    }

    private Player getSelectedPlayer() {
        GameDetailPanel gameDetailPanel = (GameDetailPanel) mainFrame.getMainPanel().getLeftComponent();
        JList playerList = gameDetailPanel.getPlayerList();
        return (Player) playerList.getSelectedValue();
    }
}
