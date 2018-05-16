package view;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {
    private Icon logo = new ImageIcon("DiceGameSADI/images/roll_dice.gif");
    private JTextField betAmount = new JTextField();
    private JButton placeBet = new JButton("Place Bet");
    private JButton roll = new JButton("Roll Dice");

    public ToolBar() {
        setLayout(new GridLayout(1, 4));

        betAmount.setSize(new Dimension(50 ,30));

        JLabel logoLabel = new JLabel(logo);
        add(logoLabel);
        add(betAmount);
        add(placeBet);
        add(roll);

        setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }
}
