package view;

import controller.GameDetailController;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class GameDetailPanel extends JSplitPane {
    private MainFrame mainFrame;

    private DefaultListModel<Player> playerModel = new DefaultListModel<>();

    private JList<Player> playerList = new JList<>(playerModel);
    private JTextArea textArea = new JTextArea("Game Progress:\n");

    public GameDetailPanel(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        playerList.addListSelectionListener(new GameDetailController(mainFrame, gameEngine));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.5);

        //add player to JList example
        Player player = new SimplePlayer("1", "hello", 100);
        playerModel.add(0, player);
        playerModel.add(1, new SimplePlayer("2", "world", 1000));

        splitPane.setLeftComponent(new JScrollPane(playerList));
        splitPane.setRightComponent(new JScrollPane(textArea));

        add(splitPane, BorderLayout.CENTER);
    }

    public JList getPlayerList() {
        return playerList;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void addPlayer(Player player) {
        playerModel.add(playerModel.getSize(), player);
    }
}
