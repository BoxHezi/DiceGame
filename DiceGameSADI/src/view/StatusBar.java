package view;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JToolBar {
    private MainFrame mainFrame;

    private JLabel left = new JLabel(" Left");
    private JLabel center = new JLabel(" Center");
    private JLabel right = new JLabel(" Right");

    public StatusBar(MainFrame mainFrame) {
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
}
