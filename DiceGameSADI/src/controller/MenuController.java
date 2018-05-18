package controller;

import model.interfaces.GameEngine;
import view.AddPlayerDialog;
import view.MenuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private GameEngine gameEngine;
    private MenuBar menuBar;

    public MenuController(MenuBar menuBar, GameEngine gameEngine) {
        this.menuBar = menuBar;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase(menuBar.getExitCommand())) {
            System.exit(1);
        } else if (event.getActionCommand().equalsIgnoreCase(menuBar.getAddPlayerCommand())) {
            new AddPlayerDialog(gameEngine);
        }
    }
}
