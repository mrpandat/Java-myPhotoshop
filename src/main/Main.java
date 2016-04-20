package main;

import javax.swing.*;
import GUI.MyLayoutExample;

public class Main {

    public static void main(String[] args) {
        MyLayoutExample layout = new MyLayoutExample();

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
