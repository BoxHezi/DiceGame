package view;

import controller.GameDetailController;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameDetailPanel extends JSplitPane {
    private DefaultListModel<Player> playerModel = new DefaultListModel<>();
    private JList<Player> playerList = new JList<>(playerModel);
    private JTextArea textArea = new JTextArea("Game Progress:\n");

    // a map that indicate if player rolled already for one round
    private Map<Player, Boolean> playerRollStatusMap = new HashMap<>();
    // a map to indicate if the playing is rolling
    private Map<Player, Boolean> playerRollingMap = new HashMap<>();

    public GameDetailPanel(MainFrame mainFrame, GameEngine gameEngine) {
        setLayout(new BorderLayout());

        GameDetailController gameDetailController = new GameDetailController(mainFrame);
        playerList.addListSelectionListener(gameDetailController);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.5);

        splitPane.setLeftComponent(new JScrollPane(playerList));
        splitPane.setRightComponent(new JScrollPane(textArea));

        add(splitPane, BorderLayout.CENTER);

        //set roll status for each player to false by default
        resetRollStatus(gameEngine);
        //set rolling status for each player to false by default
        resetRollingStatus(gameEngine);
    }

    /**
     * reset all player roll status to unroll
     *
     * @param gameEngine gameEngine to get all players information
     */
    public void resetRollStatus(GameEngine gameEngine) {
        ArrayList<Player> players = (ArrayList<Player>) gameEngine.getAllPlayers();
        for (Player player : players) {
            playerRollStatusMap.put(player, false);
        }
    }

    /**
     * reset all player rolling status to false
     *
     * @param gameEngine gameEngine to get all players information
     */
    private void resetRollingStatus(GameEngine gameEngine) {
        ArrayList<Player> players = (ArrayList<Player>) gameEngine.getAllPlayers();
        for (Player player : players) {
            playerRollingMap.put(player, false);
        }

        for (Player player : playerRollingMap.keySet()) {
            System.out.println(playerRollingMap.get(player));
        }
    }

    public JList getPlayerList() {
        return playerList;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * add player to the JList
     *
     * @param player player to be added
     */
    public void addPlayer(Player player) {
        playerModel.add(playerModel.getSize(), player);
        playerRollStatusMap.put(player, false);
        playerRollingMap.put(player, false);
    }

    public Map<Player, Boolean> getPlayerRollStatusMap() {
        return playerRollStatusMap;
    }

    public Map<Player, Boolean> getPlayerRollingMap() {
        return playerRollingMap;
    }

    /**
     * update player's roll status
     *
     * @param player player selected
     * @param rolled have the selected player rolled
     */
    public void updateRollStatus(Player player, boolean rolled) {
        playerRollStatusMap.put(player, rolled);
    }

    /**
     * update player's rolling status
     *
     * @param player  selected player
     * @param rolling is the selected player rolling or not
     */
    public void updateRollingStatus(Player player, boolean rolling) {
        playerRollingMap.put(player, rolling);
    }
}
