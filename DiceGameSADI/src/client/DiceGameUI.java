package client;

import view.implgui.GameEngineCallbackGUI;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

import javax.swing.*;

public class DiceGameUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final GameEngine gameEngine = new GameEngineImpl();
                MainFrame mainFrame = new MainFrame(gameEngine);
                gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(mainFrame, gameEngine));
//        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
            }
        });
    }
}
