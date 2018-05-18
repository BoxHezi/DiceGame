package controller;

import model.interfaces.GameEngine;
import view.MainFrame;
import view.ToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarController implements ActionListener {
    private GameEngine gameEngine;
    private MainFrame mainFrame;

    public ToolBarController(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase(mainFrame.getToolBar().getRollCommand())) {
            //call roll dice method
            System.out.println("Roll Clicked");
        } else if (event.getActionCommand().equalsIgnoreCase(mainFrame.getToolBar().getBetAmountCommand())) {
            //call bet amount method
            System.out.println("bet amount clicked");
        } else if (event.getActionCommand().equalsIgnoreCase(mainFrame.getToolBar().getPlaceBetCommand())) {
            //call place bet method
            System.out.println("place bet clicked");
        }
    }
}
