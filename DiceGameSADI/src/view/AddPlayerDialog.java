package view;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.util.ArrayList;

public class AddPlayerDialog extends JOptionPane {
    private MainFrame mainFrame;

    private String id = "";
    private String name = "";
    private String pointStr = "";

    private ArrayList<Player> players;

    public AddPlayerDialog(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;
        players = getAllPlayers(gameEngine);
        inputID(gameEngine);
    }

    private void inputID(GameEngine gameEngine) {
        do {
            id = showInputDialog("Please input ID:");
            if (null == id || id.equals(String.valueOf(CANCEL_OPTION))) {
                return;
            }

            for (Player player : players) {
                if (player.getPlayerId().equalsIgnoreCase(id)) {
                    id = "";
                    showMessageDialog(null, "ID existed! Try Again!");
                }
            }

        } while (id.matches("\\s+") || id.equals(""));
        inputName(gameEngine);
    }

    private void inputName(GameEngine gameEngine) {
        do {
            name = showInputDialog("Please enter your name:");
            if (null == name || name.equals(String.valueOf(CANCEL_OPTION))) {
                return;
            }
        } while (name.matches("\\s+") || name.equals(""));
        inputPoint(gameEngine);
    }

    private void inputPoint(GameEngine gameEngine) {
        do {
            pointStr = showInputDialog("Please enter point amount");
            if (pointStr.equals(String.valueOf(CANCEL_OPTION))) {
                return;
            }
        } while (pointStr.matches("\\D+") || pointStr.equals(""));
        confirmBox(gameEngine);
    }

    private void confirmBox(GameEngine gameEngine) {
        int confirm = showConfirmDialog(null, "Your ID is: " + id + "\nYour name is: "
                + name + "\nYour point is: " + pointStr, "Confirm", YES_NO_OPTION);
        if (confirm == YES_OPTION) {
            Player newPlayer = new SimplePlayer(id, name, Integer.valueOf(pointStr));
            //add player the GameEngine List
            gameEngine.addPlayer(newPlayer);

            //add player to GUI for user to select
            GameDetailPanel detailPanel = (GameDetailPanel) mainFrame.getMainPanel().getLeftComponent();
            detailPanel.addPlayer(newPlayer);
        }
    }

    private ArrayList<Player> getAllPlayers(GameEngine gameEngine) {
        return (ArrayList<Player>) gameEngine.getAllPlayers();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return Integer.valueOf(pointStr);
    }
}
