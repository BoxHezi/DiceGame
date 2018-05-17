package client;

import model.GameEngineCallbackGUI;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.MainFrame;

import javax.swing.*;

public class DiceGameUI {

    public static void main(String[] args) {
        final GameEngine gameEngine = new GameEngineImpl();
        gameEngine.addGameEngineCallback(new GameEngineCallbackGUI());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
            }
        });
    }
}
