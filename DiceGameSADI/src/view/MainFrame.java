package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final String GAME_RULE = "Welcome to Dice Game\n" +
            "Every player has 1000 points at the beginning,\n" +
            "you can place bet no more than your points!\n" +
            "When winning, you can get twice your placed back!\n" +
            "When losing, you will get nothing back!\n" +
            "Enjoy the Game!";

    private MainPanel mainPanel;
    private MenuBar menuBar;
    private StatusBar statusBar;
    private ToolBar toolBar;

    public MainFrame() {
        /*int i = JOptionPane.showConfirmDialog(null, "Welcome to Dice Game!\nReady to Play?",
                "Dice Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);*/
        // exit when user select no
        /*int i = JOptionPane.showConfirmDialog(null, GAME_RULE);
        if (i == JOptionPane.NO_OPTION || i == JOptionPane.CANCEL_OPTION) {
            System.exit(1);
        }*/

        mainPanel = new MainPanel();
        menuBar = new MenuBar();
        statusBar = new StatusBar();
        toolBar = new ToolBar();

        initialiseMainFrame();
    }

    private void initialiseMainFrame() {
        setTitle("DiceGame");
        setSize(500, 500);
        setMaximizedBounds(new Rectangle(0, 0, 800, 800));
        setMinimumSize(new Dimension(200, 200 ));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set frame appears in the centre of the screen
        setLocationRelativeTo(null);
        setVisible(true);

        setJMenuBar(menuBar);
        add(mainPanel, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
    }
}
