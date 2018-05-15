package view;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {
    private JTextField betAmount = new JTextField();
    private JButton confirm = new JButton("Confirm");
    private JButton roll = new JButton("Roll Dice");

    public ToolBar() {
        setLayout(new GridLayout(1, 4));

        betAmount.setSize(new Dimension(50 ,30));
        add(betAmount);
        add(confirm);
        add(roll);
    }
}
