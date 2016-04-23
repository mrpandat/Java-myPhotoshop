package GUI.view;

import GUI.controller.MainController;
import GUI.model.MainModel;
import GUI.view.layout.MyLayout;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class MainView extends JFrame implements Observer {
    private MyLayout layout;
    private JPanel panel;
    private MainModel img;

    public MainView(MainModel m ) {
        this.img = m;
        this.img.addObserver(this);
        this.layout = new MyLayout();
    }

    public void addActionsListeners(MainController controller) {
        this.layout.generateLayout(controller);
        this.panel = this.layout.getContent();
    }

    public JPanel getContent() {
        return this.layout.getContent();
    }
    public String getTitle() {
        return this.layout.getName();
    }

    @Override
    public void update(Observable o, Object arg) {
        return;
    }
}
