package view;

import controller.MenuController;
import model.interfaces.GameEngine;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuBar extends JMenuBar {
    private static final String EXIT_COMMAND = "exit";
    private static final String ADD_PLAYER_COMMAND = "add player";

    private ArrayList<JMenu> menus = new ArrayList<>();

    private JMenuItem exit = new JMenuItem("Exit");

    private JMenuItem addPlayer = new JMenuItem("Add New Player");

    private MenuController menuController;

    public MenuBar(GameEngine gameEngine) {
        menuController = new MenuController(this, gameEngine);

        JMenu file = new JMenu("File");
        file.add(exit);
        add(file);

        JMenu option = new JMenu("Option");
        option.add(addPlayer);
        add(option);

        menus.add(file);
        menus.add(option);
        initializeMenuItem(menus);
    }

    private void initializeMenuItem(List<JMenu> menus) {
        exit.setActionCommand(EXIT_COMMAND);
        addPlayer.setActionCommand(ADD_PLAYER_COMMAND);

        assignController(menus);
    }

    private void assignController(List<JMenu> menus) {
        for (JMenu menu : menus) {
            menu.addActionListener(menuController);
            menu.getItem(0);
            for (int i = 0; i < menu.getItemCount(); i++) {
                menu.getItem(i).addActionListener(menuController);
            }
        }
    }

    public String getExitCommand() {
        return EXIT_COMMAND;
    }

    public String getAddPlayerCommand() {
        return ADD_PLAYER_COMMAND;
    }
}
