package GUI.view;

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
        this.layout = new MyLayout(this.img);
        this.panel = layout.getContent();

    }

    @Override
    public void update(Observable o, Object arg) {
        return;
    }
}
