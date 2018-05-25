package client;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.MainFrame;
import view.implgui.GameEngineCallbackGUI;
import view.implgui.GameEngineCallbackImpl;

import javax.swing.*;

public class DiceGameUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final GameEngine gameEngine = new GameEngineImpl();
                gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
                MainFrame mainFrame = new MainFrame(gameEngine);
                gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(mainFrame));
            }
        });
    }
}
