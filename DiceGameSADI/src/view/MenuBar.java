package view;

import controller.MenuController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MenuBar extends JMenuBar {
    private static final String FILE_COMMAND = "file";
    private static final String EXIT_COMMAND = "exit";
    private static final String OPTION_COMMAND = "option";
    private static final String ADD_PLAYER_COMMAND = "add player";

    private ArrayList<JMenu> menus = new ArrayList<>();

    private JMenu file = new JMenu("File");
    private JMenuItem exit = new JMenuItem("Exit");

    private JMenu option = new JMenu("Option");
    private JMenuItem addPlayer = new JMenuItem("Add New Player");

    private MenuController menuController = new MenuController(this);

    public MenuBar() {
        file.add(exit);
        add(file);

        option.add(addPlayer);
        add(option);

        initializeElement();
    }

    private void initializeElement() {
        file.setActionCommand(FILE_COMMAND);
        exit.setActionCommand(EXIT_COMMAND);

        option.setActionCommand(OPTION_COMMAND);
        addPlayer.setActionCommand(ADD_PLAYER_COMMAND);

        menus.add(file);
        menus.add(option);
        assignController(menus);
    }

    private void assignController(List<JMenu> menus) {
        for (JMenu menu : menus) {
            menu.addMenuListener(menuController);
            menu.getItem(0);
            for (int i = 0; i < menu.getItemCount(); i++) {
                menu.getItem(i).addActionListener(menuController);
            }
        }
    }

    public String getFileCommand() {
        return FILE_COMMAND;
    }

    public String getExitCommand() {
        return EXIT_COMMAND;
    }

    public String getOptionCommand() {
        return OPTION_COMMAND;
    }

    public String getAddPlayerCommand() {
        return ADD_PLAYER_COMMAND;
    }

    public JMenu getFile() {
        return file;
    }

    public JMenuItem getExit() {
        return exit;
    }

    public JMenu getOption() {
        return option;
    }

    public JMenuItem getAddPlayer() {
        return addPlayer;
    }
}
