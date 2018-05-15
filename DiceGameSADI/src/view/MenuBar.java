package view;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private JMenu file = new JMenu("File");
    private JMenuItem exit = new JMenuItem("Exit");

    public MenuBar() {
        file.add(exit);
        add(file);
    }
}
