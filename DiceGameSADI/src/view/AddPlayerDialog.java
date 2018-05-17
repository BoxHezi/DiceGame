package view;

import model.GameEngineCallbackGUI;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.util.ArrayList;

public class AddPlayerDialog extends JOptionPane {
    private GameEngineCallbackGUI gui;

    private String id;
    private String name;
    private int point;

    private ArrayList<Player> players;

    /*public AddPlayerDialog(GameEngineCallbackGUI gui) {
        this.gui = gui;

        players = getAllPlayers();
        inputID();
        inputName();
        inputPoint();
        confirmBox();
    }*/
    public AddPlayerDialog() {
        players = getAllPlayers();
        inputID();
        inputName();
        inputPoint();
        confirmBox();
    }

    private void inputID() {
        while (null == id || id.equals("")) {
            id = showInputDialog("Please input ID:");
        }
        for (Player player : players) {
            if (player.getPlayerId().equalsIgnoreCase(id)) {
                System.out.println("ID existed! Try Again!");
            }
        }
    }

    private void inputName() {
        name = showInputDialog("Please enter your name:");
    }

    private void inputPoint() {
        try {
            point = Integer.parseInt(showInputDialog("Please enter point amount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void confirmBox() {
        int confirm = showConfirmDialog(null, "Your ID is: " + id + "\nYour name is: "
                + name + "\nYour point is: " + point, "Confirm", YES_NO_OPTION);
        if (confirm == YES_OPTION) {
            Player newPlayer = new SimplePlayer(id, name, point);
//            gui.getGameEngine().addPlayer(newPlayer);
            System.out.println("Player added!");
        }
    }

    private ArrayList<Player> getAllPlayers() {
        GameEngine gameEngine = new GameEngineImpl();
        return (ArrayList<Player>) gameEngine.getAllPlayers();
//        return (ArrayList<Player>) gui.getGameEngine().getAllPlayers();
    }
}
