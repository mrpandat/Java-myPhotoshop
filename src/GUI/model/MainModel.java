package GUI.model;

import GUI.controller.historic.ActionPanel;
import GUI.controller.historic.HistoricController;
import GUI.controller.panel.ImagePanel;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;


public class MainModel extends Observable {

    public JPanel mainPanel;
    public JTabbedPane panelDraw;
    public ArrayList<HistoricController> historic;

    private MainModel() {
        this.historic = new ArrayList<HistoricController>();
        this.mainPanel = new JPanel();
        this.panelDraw = new JTabbedPane();
    }

    private static MainModel INSTANCE = new MainModel();

    public static MainModel getInstance() {
        return INSTANCE;
    }

    public ImagePanel getImg() {
        return (ImagePanel) ((JPanel) this.panelDraw.getSelectedComponent()).getComponents()[0];
    }

    public HistoricController getHistoric() {
        for (HistoricController historicController : this.historic) {
            if (historicController.id == panelDraw.getSelectedIndex()) {
                return historicController;
            }
        }
        this.historic.add(new HistoricController(panelDraw.getSelectedIndex(), getImg().getImage()));
        return this.historic.get(this.historic.size() - 1);
    }


    public void setImg(BufferedImage img, ActionPanel action) {
        getHistoric().add(action);
        getImg().setImage(img);
        notifyObservers();
        setChanged();
    }

    public void setPrivateImg(BufferedImage img) {
        getImg().setImage(img);
        notifyObservers();
        setChanged();
    }

    public void undo() {
        getHistoric().undo();
        setPrivateImg(getHistoric().getHistoricImage());
        notifyObservers();
        setChanged();
    }

    public void redo() {
        getHistoric().redo();
        setPrivateImg(getHistoric().getHistoricImage());
        notifyObservers();
        setChanged();
    }

}