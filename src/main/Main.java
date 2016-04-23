package main;

import GUI.controller.MainController;
import GUI.model.MainModel;
import GUI.view.MainView;
import GUI.view.layout.MyLayout;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        MyLayout layout = new MyLayout();

        final JPanel panel = layout.getContent();
        final String title = layout.getName();
        MainModel imgmodel = new MainModel();
        MainView imageview = new MainView(imgmodel,layout);
        MainController imgctrl = new MainController(imgmodel,imageview);

        /*
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            frame.setContentPane(panel);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.pack();
        });
        */
    }
}
