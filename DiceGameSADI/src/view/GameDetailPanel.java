package view;

import model.SimplePlayer;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;

public class GameDetailPanel extends JSplitPane {
    private JList<Player> players = new JList<>();
    private JTextArea textArea = new JTextArea();

    public GameDetailPanel() {
        setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.5);

        splitPane.setLeftComponent(players);
        splitPane.setRightComponent(textArea);

        add(splitPane, BorderLayout.CENTER);
    }
}
