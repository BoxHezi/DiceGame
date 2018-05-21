package client;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.MainFrame;
import view.implgui.GameEngineCallbackGUI;

import javax.swing.*;

public class DiceGameUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final GameEngine gameEngine = new GameEngineImpl();
                MainFrame mainFrame = new MainFrame(gameEngine);
                gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(mainFrame));
//        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
            }
        });
    }
}
