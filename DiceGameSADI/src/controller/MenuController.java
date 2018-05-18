package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AddPlayerDialog;
import view.MainFrame;
import view.MenuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private GameEngine gameEngine;
    private MainFrame mainFrame;

    public MenuController(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase(mainFrame.getMenu().getExitCommand())) {
            System.exit(1);
        } else if (event.getActionCommand().equalsIgnoreCase(mainFrame.getMenu().getAddPlayerCommand())) {
            new AddPlayerDialog(mainFrame, gameEngine);
        }
    }
}
