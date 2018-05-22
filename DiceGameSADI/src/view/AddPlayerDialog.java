package view;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.util.List;

public class AddPlayerDialog extends JOptionPane {
    private MainFrame mainFrame;

    private String id = "";
    private String name = "";
    private String pointStr = "";

    public AddPlayerDialog(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;
        inputID(gameEngine);
    }

    private void inputID(GameEngine gameEngine) {
        do {
            id = showInputDialog("Please input ID:");
            if (null == id) {
                return;
            }

            List<Player> players = (List<Player>) gameEngine.getAllPlayers();
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
            if (null == name) {
                return;
            }
        } while (name.matches("\\s+") || name.equals(""));
        inputPoint(gameEngine);
    }

    private void inputPoint(GameEngine gameEngine) {
        boolean validPoint;
        do {
            pointStr = showInputDialog("Please enter point amount");
            if (null == pointStr) {
                return;
            }
            validPoint = pointValidation(pointStr);
        } while (!validPoint);
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

    private boolean pointValidation(String pointStr) {
        if (pointStr.matches("\\D+")) {
            showMessageDialog(null, "Please input integer");
            return false;
        }
        if (pointStr.equals("")) {
            showMessageDialog(null, "Please input integer");
            return false;
        }
        if (Integer.valueOf(pointStr) <= 0) {
            showMessageDialog(null, "Invalid amount, please input amount greater than 0");
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
