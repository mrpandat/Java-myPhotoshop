package main;

import GUI.controller.MainController;
import GUI.model.MainModel;
import GUI.view.MainView;
import GUI.view.layout.MyLayout;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        MainView view = new MainView();
        MainController controller = new MainController(view);

        view.addActionsListeners(controller);
        final JPanel panel = view.getContent();
        final String title = view.getName();


        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            frame.setContentPane(panel);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.pack();
        });
    }
}
