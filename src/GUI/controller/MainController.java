package GUI.controller;

import GUI.model.MainModel;
import GUI.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    public MainView imgv;
    public MainModel model = MainModel.getInstance();
    public FiltersController filtersController;
    public MenuController menuController;

    public MainController(MainView imgv) {
        this.filtersController = new FiltersController();
        this.menuController = new MenuController();
        this.imgv = imgv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        return;
    }


}
