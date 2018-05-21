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
        splitPane.setRightComponent(new DicePanel(mainFrame));

        add(splitPane, BorderLayout.CENTER);
    }

    public Component getRightComponent() {
        return splitPane.getRightComponent();
    }

    public Component getLeftComponent() {
        return splitPane.getLeftComponent();
    }
}
