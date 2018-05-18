package controller;

import model.interfaces.GameEngine;
import view.GameDetailPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameDetailController implements ActionListener {
    private GameEngine gameEngine;
    private GameDetailPanel gameDetailPanel;

    public GameDetailController(GameDetailPanel panel, GameEngine gameEngine) {
        this.gameDetailPanel = panel;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
