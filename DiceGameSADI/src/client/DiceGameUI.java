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
        final GameEngine gameEngine = new GameEngineImpl();
        gameEngine.addGameEngineCallback(new GameEngineCallbackGUI());
//        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

        Player[] players = new Player[]
                {new SimplePlayer("1", "The Roller", 1000)};
        gameEngine.addPlayer(players[0]);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame(gameEngine);
            }
        });
    }
}
