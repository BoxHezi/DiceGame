package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolBarController extends MouseAdapter {
    @Override
    public void mouseEntered(MouseEvent event) {
        System.out.println(event.getComponent());
    }
}
