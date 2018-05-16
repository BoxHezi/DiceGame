package controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolBarController extends MouseAdapter {
    /*@Override
    public void mouseEntered(MouseEvent event) {
        System.out.println(event.getComponent());
    }*/

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getSource() instanceof JButton) {
            JButton button = (JButton) event.getSource();
            if (button.getText().equalsIgnoreCase("Place Bet")) {
                System.out.println("-----------");
            }
        }
    }
}
