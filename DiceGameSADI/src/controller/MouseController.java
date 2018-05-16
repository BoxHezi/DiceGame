package controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseController extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getComponent() instanceof JMenuItem) {
            JMenuItem menuItem = (JMenuItem) event.getSource();
            if (menuItem.getText().equalsIgnoreCase("exit")) {
                System.exit(0);
            }
        }
    }
}
