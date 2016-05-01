package GUI.controller;

import GUI.controller.filters.FiltersController;
import GUI.controller.historic.ActionPanel;
import GUI.controller.menus.MenuController;
import GUI.model.MainModel;
import GUI.view.MainView;
import filter.Filter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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

    public void applyFilter(Filter f) {
        MainModel m = MainModel.getInstance();
        BufferedImage bi = f.perform(m.getImg().getImage());
        m.setImg(bi,new ActionPanel(f.getName(),bi));
        m.notifyObservers();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        return;
    }


}
