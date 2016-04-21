package main;

import javax.swing.*;
import GUI.MyLayout;

public class Main {

    public static void main(String[] args) {
        MyLayout layout = new MyLayout();

        final JPanel panel = layout.getContent();
        final String title = layout.getName();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            frame.setContentPane(panel);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.pack();
        });
    }
}
