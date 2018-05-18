package view;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;

public class GameDetailPanel extends JSplitPane {
    private DefaultListModel playerModel = new DefaultListModel();

    private JList playerList = new JList(playerModel);
    private JTextArea textArea = new JTextArea("Game Progress:\n");

    public GameDetailPanel() {
        setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.5);

        //add player to JList example
        Player player = new SimplePlayer("1", "hello", 100);
        playerModel.add(0, player);
        playerModel.add(1, new SimplePlayer("2", "world", 1000));

        splitPane.setLeftComponent(playerList);
        splitPane.setRightComponent(textArea);

        add(splitPane, BorderLayout.CENTER);
    }

    public JList getPlayerList() {
        return playerList;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void addPlayer(Player player) {

    }
}
