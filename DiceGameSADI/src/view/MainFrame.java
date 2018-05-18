package view;

import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private GameEngine gameEngine;

    private MainPanel mainPanel;
    private MenuBar menuBar;
    private StatusBar statusBar;
    private ToolBar toolBar;

    public MainFrame(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        mainPanel = new MainPanel();
        menuBar = new MenuBar(gameEngine);
        statusBar = new StatusBar();
        toolBar = new ToolBar();

        initialiseMainFrame();
    }

    private void initialiseMainFrame() {
        setTitle("DiceGame");
        setSize(500, 500);
        setMaximizedBounds(new Rectangle(0, 0, 800, 800));
        setMinimumSize(new Dimension(200, 200));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set frame appears in the centre of the screen
        setLocationRelativeTo(null);
        setVisible(true);

        setJMenuBar(menuBar);
        add(mainPanel, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }
}
