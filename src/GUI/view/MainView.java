package GUI.view;

import GUI.controller.MainController;
import GUI.model.MainModel;
import GUI.view.layout.MyLayout;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class MainView extends JFrame implements Observer {
    private MyLayout layout;
    private MainController controller;

    public MainView() {
        this.layout = new MyLayout();
        MainModel.getInstance().addObserver(this);
    }

    public void addActionsListeners(MainController controller) {
        this.controller = controller;
        this.layout.generateLayout(controller);
        MainModel.getInstance().mainPanel = this.layout.getContent();
    }

    public JPanel getContent() {
        return this.layout.getContent();
    }

    public String getTitle() {
        return this.layout.getName();
    }

    @Override
    public void update(Observable o, Object arg) {
        MainModel.getInstance().getImg().repaint();
        MainModel.getInstance().panelDraw.getSelectedComponent().repaint();

        return;
    }
}
