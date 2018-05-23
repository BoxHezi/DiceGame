package view;

import controller.ToolBarController;
import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {
    private static final String PLACE_BET_COMMAND = "Place Bet";
    private static final String ROLL_COMMAND = "Roll Dice";
    private static final String HOUSE_ROLL_COMMAND = "House Roll";

    private JTextField betAmount = new JTextField();
    private JButton placeBet = new JButton(PLACE_BET_COMMAND);
    private JButton rollButton = new JButton(ROLL_COMMAND);
    private JButton houseRollButton = new JButton(HOUSE_ROLL_COMMAND);

    private ToolBarController toolBarController;

    public ToolBar(MainFrame mainFrame, GameEngine gameEngine) {
        toolBarController = new ToolBarController(mainFrame, gameEngine);

        initializeElement();
        setLayout(new GridLayout(1, 4));
        setFloatable(false);

        Icon logo = new ImageIcon("DiceGameSADI/images/roll_dice.gif");
        JLabel logoLabel = new JLabel(logo);

        add(logoLabel);
        add(betAmount);
        add(placeBet);
        add(rollButton);
        add(houseRollButton);

        setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    private void initializeElement() {
        placeBet.setActionCommand(PLACE_BET_COMMAND);
        rollButton.setActionCommand(ROLL_COMMAND);
        houseRollButton.setActionCommand(HOUSE_ROLL_COMMAND);

        placeBet.addActionListener(toolBarController);
        rollButton.addActionListener(toolBarController);
        houseRollButton.addActionListener(toolBarController);

        rollButton.setEnabled(false);
        houseRollButton.setEnabled(false);
    }

    public String getPlaceBetCommand() {
        return PLACE_BET_COMMAND;
    }

    public String getRollCommand() {
        return ROLL_COMMAND;
    }

    public String getHouseRollCommand() {
        return HOUSE_ROLL_COMMAND;
    }

    public JTextField getBetAmountText() {
        return betAmount;
    }

    public JButton getPlaceBetButton() {
        return placeBet;
    }

    public JButton getRollButton() {
        return rollButton;
    }

    public JButton getHouseRollButton() {
        return houseRollButton;
    }
}
