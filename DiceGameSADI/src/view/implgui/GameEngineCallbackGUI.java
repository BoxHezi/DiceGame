package view.implgui;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.DicePanel;
import view.MainFrame;
import view.implgui.interfaces.GameEngineCallback;

import javax.swing.*;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private MainFrame mainFrame;

    public GameEngineCallbackGUI(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine) {
        rolling(dicePair);
    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine) {
        displayResult(result);
    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
        rolling(dicePair);
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine) {
        displayResult(result);
    }

    private void rolling(DicePair dicePair) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DicePanel dicePanel = (DicePanel) mainFrame.getMainPanel().getRightComponent();
                int totalDiceValue = dicePair.getDice1() + dicePair.getDice2();
                dicePanel.getTotalValue().setText(String.valueOf(totalDiceValue));

                dicePanel.updateDiceImg(dicePair);
            }
        });
    }

    private void displayResult(DicePair result) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DicePanel dicePanel = (DicePanel) mainFrame.getMainPanel().getRightComponent();
                int totalDiceValue = result.getDice1() + result.getDice2();
                dicePanel.getTotalValue().setText(String.valueOf(totalDiceValue));

                mainFrame.getToolBar().getPlaceBetButton().setEnabled(true);
                mainFrame.getToolBar().getRollButton().setEnabled(false);

                dicePanel.updateDiceImg(result);
            }
        });
    }
}
