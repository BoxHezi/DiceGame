package view;

import javax.swing.*;
import java.awt.*;

public class DicePanel extends JPanel {
    private MainFrame mainFrame;

    private String[] diceArray = new String[]{"DiceGameSADI/images/dice1.png",
            "DiceGameSADI/images/dice2.png",
            "DiceGameSADI/images/dice3.png",
            "DiceGameSADI/images/dice4.png",
            "DiceGameSADI/images/dice5.png",
            "DiceGameSADI/images/dice6.png"};

    private JLabel dice1 = new JLabel(new ImageIcon("DiceGameSADI/images/dice1.png"));
    private JLabel dice2 = new JLabel(new ImageIcon("DiceGameSADI/images/dice2.png"));
    private JLabel totalValue = new JLabel("0");

    public DicePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new FlowLayout());
        dicePanel.add(dice1);
        dicePanel.add(dice2);
        add(dicePanel, BorderLayout.NORTH);

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout());
        totalPanel.add(new JLabel("Total: "));
        totalPanel.add(totalValue);
        add(totalPanel, BorderLayout.CENTER);
    }

    public JLabel getDice1() {
        return dice1;
    }

    public JLabel getDice2() {
        return dice2;
    }

    public JLabel getTotalValue() {
        return totalValue;
    }

    public String[] getDiceArray() {
        return diceArray;
    }
}
