package GUI.view.layout;

import javax.swing.*;
import java.awt.*;

public class MyToolbar extends JPanel {
    public JPanel mainPanel;

    MyToolbar(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void generateButtons() {
        setBounds(60, 400, 220, 30);
        add(new JButton("draw"));
        add(new JButton("Erase"));

        setVisible(true);
    }

    public void paint() {

    }
}
