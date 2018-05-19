package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameDetailPanel;
import view.MainFrame;
import view.StatusBar;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameDetailController implements ListSelectionListener {
    private GameEngine gameEngine;
    private MainFrame mainFrame;

    public GameDetailController(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList) e.getSource();
        Player selectedPlayer = (Player) list.getSelectedValue();
        System.out.println(selectedPlayer.getPlayerName());

        StatusBar statusBar = mainFrame.getStatusBar();
        statusBar.displayPlayerInfo(selectedPlayer);
    }
}
