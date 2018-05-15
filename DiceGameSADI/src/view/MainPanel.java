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

        splitPane.setResizeWeight(0.6);
        //disable resize left and right panel
        splitPane.setEnabled(true);

        splitPane.setRightComponent(new DicePanel());

        add(splitPane, BorderLayout.CENTER);
    }
}
