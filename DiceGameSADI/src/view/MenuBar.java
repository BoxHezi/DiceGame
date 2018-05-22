package view;

import controller.MenuController;
import model.interfaces.GameEngine;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuBar extends JMenuBar {
    private static final String EXIT_COMMAND = "exit";
    private static final String ADD_PLAYER_COMMAND = "add player";
    private static final String SAVE_GAME_COMMAND = "save game";
    private static final String LOAD_GAME_COMMAND = "load game";

    private JMenuItem loadGame = new JMenuItem("Load Game");
    private JMenuItem saveGame = new JMenuItem("Save Game");
    private JMenuItem exit = new JMenuItem("Exit");

    private JMenuItem addPlayer = new JMenuItem("Add New Player");

    private MenuController menuController;

    public MenuBar(MainFrame mainFrame, GameEngine gameEngine) {
        this.menuController = new MenuController(mainFrame, gameEngine);

        JMenu file = new JMenu("File");
        file.add(loadGame);
        file.add(saveGame);
        file.add(exit);
        add(file);

        JMenu option = new JMenu("Option");
        option.add(addPlayer);
        add(option);

        ArrayList<JMenu> menus = new ArrayList<>();
        menus.add(file);
        menus.add(option);
        initializeMenuItem(menus);
    }

    private void initializeMenuItem(List<JMenu> menus) {
        loadGame.setActionCommand(LOAD_GAME_COMMAND);
        saveGame.setActionCommand(SAVE_GAME_COMMAND);
        exit.setActionCommand(EXIT_COMMAND);
        addPlayer.setActionCommand(ADD_PLAYER_COMMAND);

        assignController(menus);
    }

    private void assignController(List<JMenu> menus) {
        for (JMenu menu : menus) {
            menu.addActionListener(menuController);
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

    public String getSaveGameCommand() {
        return SAVE_GAME_COMMAND;
    }

    public String getLoadGameCommand() {
        return LOAD_GAME_COMMAND;
    }
}
