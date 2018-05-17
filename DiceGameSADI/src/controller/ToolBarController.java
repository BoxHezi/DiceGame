package controller;

import view.ToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarController implements ActionListener {
    private ToolBar toolBar;

    public ToolBarController(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase(toolBar.getRollCommand())) {
            //call roll dice method
            System.out.println("Roll Clicked");
        } else if (event.getActionCommand().equalsIgnoreCase(toolBar.getBetAmountCommand())) {
            //call bet amount method
            System.out.println("bet amount clicked");
        } else if (event.getActionCommand().equalsIgnoreCase(toolBar.getPlaceBetCommand())) {
            //call place bet method
            System.out.println("place bet clicked");
        }
    }
}
