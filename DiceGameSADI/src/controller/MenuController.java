package controller;

import view.AddPlayerDialog;
import view.MenuBar;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements MenuListener, ActionListener {
    private MenuBar menuBar;

    public MenuController(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    @Override
    public void menuSelected(MenuEvent event) {
        JMenu menu = (JMenu) event.getSource();
        System.out.println(menu.getText());
        for (int i = 0; i < menu.getItemCount(); i++) {
            System.out.println(menu.getItem(i).getText());
        }
    }

    @Override
    public void menuDeselected(MenuEvent event) {

    }

    @Override
    public void menuCanceled(MenuEvent event) {

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase(menuBar.getExitCommand())) {
            System.exit(1);
        } else if (event.getActionCommand().equalsIgnoreCase(menuBar.getAddPlayerCommand())) {
            System.out.println("add player");
            new AddPlayerDialog();
        }
    }
}
