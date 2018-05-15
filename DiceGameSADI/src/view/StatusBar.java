package view;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JToolBar {
    private JLabel left = new JLabel("Left");
    private JLabel center = new JLabel("Center");
    private JLabel right = new JLabel("Right");

    public StatusBar() {
        setLayout(new GridLayout(1, 3));
        add(left);
        add(center);
        add(right);
    }
}
