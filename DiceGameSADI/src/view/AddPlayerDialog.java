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

    /**
     * method to input new player's ID, validation check for ID will be done as well
     *
     * @param gameEngine gameEngine to pass tp inputName
     */
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

    /**
     * method to input name
     *
     * @param gameEngine gameEngine to pass to inputPoint
     */
    private void inputName(GameEngine gameEngine) {
        do {
            name = showInputDialog("Please enter your name:");
            if (null == name) {
                return;
            }
        } while (name.matches("\\s+") || name.equals(""));
        inputPoint(gameEngine);
    }

    /**
     * method to input new player's point
     *
     * @param gameEngine gameEngine pass to confirmBox
     */
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

    /**
     * confirm input date, if cancel is pressed, nothing will be done
     *
     * @param gameEngine gameEngine need to retrieve player list
     */
    private void confirmBox(GameEngine gameEngine) {
        int confirm = showConfirmDialog(null, "Your ID is: " + id + "\nYour name is: "
                + name + "\nYour point is: " + pointStr, "Confirm", YES_NO_OPTION);
        if (confirm == YES_OPTION) {
            Player newPlayer = new SimplePlayer(id, name, Integer.valueOf(pointStr));
            //add player the GameEngine List
            gameEngine.addPlayer(newPlayer);

            //add player to GUI for user to select
            GameDetailPanel detailPanel = mainFrame.getMainPanel().getLeftComponent();
            detailPanel.addPlayer(newPlayer);
        }
    }

    /**
     * check if player input valid point
     *
     * @param pointStr point in String which need to be converted to integer
     * @return true if the point enter is acceptable
     */
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
