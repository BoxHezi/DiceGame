package view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        try {
            setLayout(new BorderLayout());
            ImageIcon imageIcon = new ImageIcon("DiceGameSADI/images/dice1.png");
            JLabel label = new JLabel(imageIcon);

            Image image = imageIcon.getImage();
            label.setIcon(new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

            this.add(label, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
