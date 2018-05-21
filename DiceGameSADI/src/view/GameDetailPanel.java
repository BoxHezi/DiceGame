package view;

import controller.GameDetailController;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameDetailPanel extends JSplitPane {
    private DefaultListModel<Player> playerModel = new DefaultListModel<>();
    private JList<Player> playerList = new JList<>(playerModel);
    private JTextArea textArea = new JTextArea("Game Progress:\n");

    // a map that indicate if player rolled already for one round
    private HashMap<Player, Boolean> playerRollMap = new HashMap<>();

    public GameDetailPanel(MainFrame mainFrame, GameEngine gameEngine) {
        setLayout(new BorderLayout());

        playerList.addListSelectionListener(new GameDetailController(mainFrame, gameEngine));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.5);

        splitPane.setLeftComponent(new JScrollPane(playerList));
        splitPane.setRightComponent(new JScrollPane(textArea));

        add(splitPane, BorderLayout.CENTER);

        //set roll status for each player to false by default
        resetRollStatus(gameEngine);
    }

    public void resetRollStatus(GameEngine gameEngine) {
        ArrayList<Player> players = (ArrayList<Player>) gameEngine.getAllPlayers();
        for (Player player : players) {
            playerRollMap.put(player, false);
        }
    }

    public JList getPlayerList() {
        return playerList;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void addPlayer(Player player) {
        playerModel.add(playerModel.getSize(), player);
        playerRollMap.put(player, false);
    }

    public HashMap<Player, Boolean> getPlayerRollMap() {
        return playerRollMap;
    }

    public void updateRollStatus(Player player, boolean rolled) {
        playerRollMap.put(player, rolled);
    }
}
