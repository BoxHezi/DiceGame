package view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JSplitPane splitPane = new JSplitPane();

    public MainPanel() {
        initializeMainPanel();
        /*Image image = imageIcon.getImage();
        label.setIcon(new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));*/
    }

    private void initializeMainPanel() {
        setLayout(new BorderLayout());

        //disable resize left and right panel
        splitPane.setEnabled(false);

        splitPane.setRightComponent(new DicePanel());
        splitPane.setLeftComponent(new GameDetailPanel());

        add(splitPane, BorderLayout.CENTER);
    }

    public Component getRightComponent() {
        return splitPane.getRightComponent();
    }

    public Component getLeftComponent() {
        return splitPane.getLeftComponent();
    }
}
