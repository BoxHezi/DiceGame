package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameDetailPanel;
import view.MainFrame;
import view.StatusBar;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GameDetailController implements ListSelectionListener {
    private GameEngine gameEngine;
    private MainFrame mainFrame;

    public GameDetailController(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        JList list = (JList) e.getSource();
        Player selectedPlayer = (Player) list.getSelectedValue();
        if (null != selectedPlayer) {
            System.out.println(selectedPlayer.getPlayerName());

            StatusBar statusBar = mainFrame.getStatusBar();
            statusBar.displayPlayerInfo(selectedPlayer);

            updateButtonStatus(selectedPlayer);
        }
    }

    private void updateButtonStatus(Player player) {
        boolean rolled = getPlayerRolledStatus(player);
        System.out.println("rolled: " + rolled);
        int betAmount = player.getBet();

        //check if player roll dice in the round
        if (rolled) {
            mainFrame.getToolBar().getPlaceBetButton().setEnabled(false);
            mainFrame.getToolBar().getRollButton().setEnabled(false);
        } else {
            if (betAmount <=0 ) {
                mainFrame.getToolBar().getPlaceBetButton().setEnabled(true);
                mainFrame.getToolBar().getRollButton().setEnabled(false);
            } else {
                mainFrame.getToolBar().getPlaceBetButton().setEnabled(false);
                mainFrame.getToolBar().getRollButton().setEnabled(true);
            }
        }
    }

    private boolean getPlayerRolledStatus(Player player) {
        GameDetailPanel gameDetailPanel = (GameDetailPanel) mainFrame.getMainPanel().getLeftComponent();
        return gameDetailPanel.getPlayerRollMap().get(player);
    }
}
