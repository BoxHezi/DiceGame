package view;

import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JSplitPane splitPane = new JSplitPane();
    private MainFrame mainFrame;

    public MainPanel(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        splitPane.setResizeWeight(0.5);
        //disable resize left and right panel
        splitPane.setEnabled(false);

        splitPane.setRightComponent(new DicePanel(mainFrame));
        splitPane.setLeftComponent(new GameDetailPanel(mainFrame, gameEngine));

        add(splitPane, BorderLayout.CENTER);
        /*Image image = imageIcon.getImage();
        label.setIcon(new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));*/
    }

    public Component getRightComponent() {
        return splitPane.getRightComponent();
    }

    public Component getLeftComponent() {
        return splitPane.getLeftComponent();
    }
}
