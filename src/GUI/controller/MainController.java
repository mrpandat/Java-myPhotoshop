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
        if (model.panelDraw.getTabCount() <= 0) return;
        MainModel m = MainModel.getInstance();
        Thread t = new Thread() {
            public void run() {
                this.setName(f.getName());
                BufferedImage bi = f.perform(m.getImg().getImage());
                applyModification(bi, new ActionPanel(f.getName(), bi));
                m.notifyObservers();
                model.filterThread.remove(this);
            }
        };
        model.filterThread.add(t);
        t.start();

    }


    public static void  applyModification(BufferedImage bi, ActionPanel a) {
        MainModel m = MainModel.getInstance();
        if (m.panelDraw.getTabCount() <= 0) return;
        m.setImg(bi, a);
        m.notifyObservers();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        return;
    }


}
