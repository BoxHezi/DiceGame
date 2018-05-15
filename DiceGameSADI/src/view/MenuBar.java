package view;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private JMenu file;
    private JMenuItem exit;

    public MenuBar() {
        file = new JMenu("File");
        exit = new JMenuItem("Exit");

        file.add(exit);
        add(file);
    }
}
