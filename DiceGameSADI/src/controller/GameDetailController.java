package controller;

import model.interfaces.Player;
import view.DicePanel;
import view.GameDetailPanel;
import view.MainFrame;
import view.StatusBar;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GameDetailController implements ListSelectionListener {
    private MainFrame mainFrame;

    public GameDetailController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        JList list = (JList) e.getSource();
        Player selectedPlayer = (Player) list.getSelectedValue();
        if (null != selectedPlayer) {
            StatusBar statusBar = mainFrame.getStatusBar();
            statusBar.displayPlayerInfo(selectedPlayer);

            updateUIStatus(selectedPlayer);
        }
    }

    /**
     * update UI for selected player to limit the player option
     *
     * @param player selected player
     */
    private void updateUIStatus(Player player) {
        boolean rolling = getPlayerRollingStatus(player);
        boolean rolled = getPlayerRolledStatus(player);
        int betAmount = player.getBet();

        //if the selected player is rolling, disable buttons to avoid unexpected behaviours
        if (rolling) {
            mainFrame.getToolBar().getPlaceBetButton().setEnabled(false);
            mainFrame.getToolBar().getRollButton().setEnabled(false);
            return;
        }

        //check if player rolled dice already in the round
        if (rolled) {
            //set all button for player to false if the player has rolled
            mainFrame.getToolBar().getPlaceBetButton().setEnabled(false);
            mainFrame.getToolBar().getRollButton().setEnabled(false);

            DicePanel dicePanel = mainFrame.getMainPanel().getRightComponent();
            dicePanel.setDiceImg(player);
        } else {
            //if the player placed bet, set roll button to true, otherwise false
            if (betAmount <= 0) {
                mainFrame.getToolBar().getPlaceBetButton().setEnabled(true);
                mainFrame.getToolBar().getRollButton().setEnabled(false);
            } else {
                mainFrame.getToolBar().getPlaceBetButton().setEnabled(false);
                mainFrame.getToolBar().getRollButton().setEnabled(true);
            }
        }
    }

    /**
     * check if have the selected player rolled in the current round
     *
     * @param player selected player
     * @return true if the player has rolled for the current round
     */
    private boolean getPlayerRolledStatus(Player player) {
        GameDetailPanel gameDetailPanel = mainFrame.getMainPanel().getLeftComponent();
        return gameDetailPanel.getPlayerRollStatusMap().get(player);
    }

    /**
     * check if the selected player rolling
     *
     * @param player selected player
     * @return true if the player is rolling currently
     */
    private boolean getPlayerRollingStatus(Player player) {
        GameDetailPanel gameDetailPanel = mainFrame.getMainPanel().getLeftComponent();
        return gameDetailPanel.getPlayerRollingMap().get(player);
    }
}
