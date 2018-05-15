package view;

import javax.swing.*;
import java.awt.*;

public class DicePanel extends JPanel {
    private JLabel dice1 = new JLabel(new ImageIcon("DiceGameSADI/images/dice1.png"));
    private JLabel dice2 = new JLabel(new ImageIcon("DiceGameSADI/images/dice2.png"));
    private JLabel total = new JLabel("Total: ");
    private JLabel totalValue = new JLabel("0");

    public DicePanel() {
        setLayout(new BorderLayout());

        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new FlowLayout());
        dicePanel.add(dice1);
        dicePanel.add(dice2);
        add(dicePanel, BorderLayout.NORTH);

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout());
        totalPanel.add(total);
        totalPanel.add(totalValue);
        add(totalPanel, BorderLayout.CENTER);
    }
}
