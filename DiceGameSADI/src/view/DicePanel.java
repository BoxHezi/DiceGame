package view;

import model.interfaces.DicePair;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;

public class DicePanel extends JPanel {
    private String[] diceArray = new String[]{"DiceGameSADI/images/dice1.png",
            "DiceGameSADI/images/dice2.png",
            "DiceGameSADI/images/dice3.png",
            "DiceGameSADI/images/dice4.png",
            "DiceGameSADI/images/dice5.png",
            "DiceGameSADI/images/dice6.png"};

    private JLabel dice1 = new JLabel(new ImageIcon(diceArray[0]));
    private JLabel dice2 = new JLabel(new ImageIcon(diceArray[0]));
    private JLabel totalValue = new JLabel("0");

    public DicePanel(MainFrame mainFrame) {
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

    /**
     *  update dice image to show the dice rolling
     * @param dicePair dice pair to get dice result
     */
    public void updateDiceImg(DicePair dicePair) {
        int valueIndex1 = dicePair.getDice1() - 1;
        int valueIndex2 = dicePair.getDice2() - 1;

        dice1.setIcon(new ImageIcon(diceArray[valueIndex1]));
        dice2.setIcon(new ImageIcon(diceArray[valueIndex2]));
    }

    /**
     *  if the player selected has rolled already, show the dice result image
     * @param player selected player
     */
    public void setDiceImg(Player player) {
        DicePair playerResult = player.getRollResult();
        updateDiceImg(playerResult);
    }
}
