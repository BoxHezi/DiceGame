package controller;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AddPlayerDialog;
import view.GameDetailPanel;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class MenuController extends JOptionPane implements ActionListener {
    private static final String GAME_RECORD_FILE = "dicegame.sav";

    private GameEngine gameEngine;
    private MainFrame mainFrame;

    public MenuController(MainFrame mainFrame, GameEngine gameEngine) {
        this.mainFrame = mainFrame;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase(mainFrame.getMenu().getExitCommand())) {
            System.exit(0);
        } else if (event.getActionCommand().equalsIgnoreCase(mainFrame.getMenu().getAddPlayerCommand())) {
            new AddPlayerDialog(mainFrame, gameEngine);
        } else if (event.getActionCommand().equalsIgnoreCase(mainFrame.getMenu().getSaveGameCommand())) {
            // output game info to file
            saveGame(gameEngine);
        } else if (event.getActionCommand().equalsIgnoreCase(mainFrame.getMenu().getLoadGameCommand())) {
            // load file
            loadGame(gameEngine);
        }
    }

    /**
     *  load game information from file
     *  if there are some player in the game engine, reject to load
     * @param gameEngine game engine to add load player information
     */
    private void loadGame(GameEngine gameEngine) {
        GameDetailPanel gameDetailPanel = (GameDetailPanel) mainFrame.getMainPanel().getLeftComponent();
        JList playerList = gameDetailPanel.getPlayerList();
        //if there are already have player(s), reject load to avoid duplicate player ID
        if (playerList.getModel().getSize() > 0) {
            showMessageDialog(null, "Already have player(s), cannot load the file!");
            return;
        }

        Scanner loader = null;

        try {
            loader = new Scanner(new File(GAME_RECORD_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (null != loader) {
            while (loader.hasNextLine()) {
                String line = loader.nextLine();
                String[] playerInfo = line.split(",");
                Player player = new SimplePlayer(playerInfo[0], playerInfo[1], Integer.valueOf(playerInfo[2]));
                //add player to player list in game engine
                gameEngine.addPlayer(player);

                //add player to the game detail panel
                gameDetailPanel.addPlayer(player);
            }
        }
    }

    /**
     *  save game info to a file
     * @param gameEngine game engine to retrieve game information
     */
    private void saveGame(GameEngine gameEngine) {
        PrintWriter printWriter = null;

        try {
           printWriter = new PrintWriter(GAME_RECORD_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (null != printWriter) {
            List<Player> players = (List<Player>) gameEngine.getAllPlayers();
            for (Player player : players) {
                printWriter.println(player.getPlayerId() + "," + player.getPlayerName() + "," + player.getPoints());
            }
            printWriter.close();
            System.exit(0);
        }
    }
}
