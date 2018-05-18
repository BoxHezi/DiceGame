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
        } while (id.matches("\\s+") || id.equals(""));

        for (Player player : players) {
            if (player.getPlayerId().equalsIgnoreCase(id)) {
                System.out.println("ID existed! Try Again!");
            }
        }
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
            pointStr = showInputDialog("Please enter pointStr amount");
            System.out.println("Point: " + pointStr);
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
            gameEngine.addPlayer(newPlayer);
            System.out.println("Player added!");
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
