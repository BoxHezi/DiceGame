package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JToolBar {
    private MainFrame mainFrame;

    private JLabel left = new JLabel(" Left");
    private JLabel center = new JLabel(" Center");
    private JLabel right = new JLabel(" Right");

    public StatusBar(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;

        setLayout(new GridLayout(1, 3));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

        left.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        center.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        right.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(left);
        add(center);
        add(right);
    }

    public JLabel getLeft() {
        return left;
    }

    public JLabel getCenter() {
        return center;
    }

    public JLabel getRight() {
        return right;
    }

    public void displayPlayerInfo(Player player) {
        left.setText(" " + player.getPlayerId());
        center.setText(" " + player.getPlayerName());
        right.setText(String.valueOf(" " + player.getPoints()));
    }
}
