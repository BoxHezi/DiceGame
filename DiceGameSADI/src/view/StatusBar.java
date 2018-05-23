package view;

import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JToolBar {
    private JLabel idStatus = new JLabel(" ID");
    private JLabel nameStatus = new JLabel(" Name");
    private JLabel pointStatus = new JLabel(" Point");

    public StatusBar() {
        setLayout(new GridLayout(1, 3));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

        idStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        nameStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pointStatus.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(idStatus);
        add(nameStatus);
        add(pointStatus);
    }

    public JLabel getPointStatus() {
        return pointStatus;
    }

    public void displayPlayerInfo(Player player) {
        idStatus.setText(" " + player.getPlayerId());
        nameStatus.setText(" " + player.getPlayerName());
        pointStatus.setText(String.valueOf(" " + player.getPoints()));
    }
}
