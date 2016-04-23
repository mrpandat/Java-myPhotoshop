package GUI.controller;

import GUI.model.MainModel;
import GUI.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pandat on 22/04/16.
 */
public class MainController implements ActionListener {
    public MainModel imgm;
    public MainView imgv;

    public MainController(MainModel imgm, MainView imgv) {
        this.imgm = imgm;
        this.imgv = imgv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("receive event !!!");
        System.out.println(e.getSource());
        return;
    }
}
