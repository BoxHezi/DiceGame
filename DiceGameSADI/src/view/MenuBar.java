package view;

import controller.MenuController;
import controller.MouseController;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private JMenu file = new JMenu("File");
    private JMenuItem exit = new JMenuItem("Exit");

    private JMenu option = new JMenu("Option");
    private JMenuItem addPlayer = new JMenuItem("Add New Player");

    public MenuBar() {
        file.addMenuListener(new MenuController());
        exit.addMouseListener(new MouseController());
        file.add(exit);
        add(file);

        option.add(addPlayer);
        add(option);
    }
}
