package view;

import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JSplitPane splitPane = new JSplitPane();

    public MainPanel(MainFrame mainFrame, GameEngine gameEngine) {
        setLayout(new BorderLayout());

        splitPane.setResizeWeight(0.5);
        //disable resize left and right panel
        splitPane.setEnabled(false);

        splitPane.setLeftComponent(new GameDetailPanel(mainFrame, gameEngine));
        splitPane.setRightComponent(new DicePanel());

        add(splitPane, BorderLayout.CENTER);
    }

    public DicePanel getRightComponent() {
        return (DicePanel) splitPane.getRightComponent();
    }

    public GameDetailPanel getLeftComponent() {
        return (GameDetailPanel) splitPane.getLeftComponent();
    }
}
