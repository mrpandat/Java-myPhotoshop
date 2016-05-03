package GUI.model;

import GUI.controller.historic.ActionPanel;
import GUI.controller.historic.HistoricController;
import GUI.controller.panel.ImagePanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;


public class MainModel extends Observable {

    public JPanel mainPanel;
    public JTabbedPane panelDraw;
    public JLabel statusBar;
    public ArrayList<HistoricController> historic;
    public ArrayList<Thread> filterThread = new ArrayList<Thread>();

    private MainModel() {
        this.historic = new ArrayList<HistoricController>();
        this.mainPanel = new JPanel();
        this.panelDraw = new JTabbedPane();
        this.statusBar = new JLabel("Welcome to myphotoshop :)");

        panelDraw.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (panelDraw.getTabCount() <= 0) {
                    statusBar.setText("");
                    return;
                }
                statusBar.setText(getHistoric().getLastHistoricName());
            }
        });
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


    public void setHistoric(HistoricController historic) {
        this.historic.add(historic);
        historic.setId(this.historic.size() - 1);
    }


    public void setImg(BufferedImage img, ActionPanel action) {
        if (getHistoric().isEmpty()) return;
        getHistoric().add(action);
        getImg().setImage(img);
        notifyObservers();
        setChanged();
        statusBar.setText(getHistoric().getLastHistoricName());
    }

    public void setPrivateImg(BufferedImage img) {
        getImg().setImage(img);
        notifyObservers();
        setChanged();
        statusBar.setText(getHistoric().getLastHistoricName());
    }

    public void undo() {
        if (this.panelDraw.getTabCount() <= 0) return;

        if (getHistoric().isEmpty()) return;
        getHistoric().undo();
        setPrivateImg(getHistoric().getHistoricImage());
        notifyObservers();
        setChanged();
        if (!getHistoric().isLast())
            setStatusBar("Undo to " + getStatusBar());
    }

    public void redo() {
        if (this.panelDraw.getTabCount() <= 0) return;

        getHistoric().redo();
        setPrivateImg(getHistoric().getHistoricImage());
        notifyObservers();
        setChanged();
        if (!getHistoric().isLast())
            setStatusBar("Redo to " + getStatusBar());
    }

    public void cancelFilter() {
        if (filterThread.isEmpty()) return;
        this.filterThread.get(this.filterThread.size() - 1).stop();
        this.filterThread.remove(this.filterThread.size() - 1);
    }

    public void setStatusBar(String text) {
        this.statusBar.setText(text);
    }

    public String getStatusBar() {
        return this.statusBar.getText();
    }

    public void deleteHistoric() {
        if (historic.size() <= 0) return;
        historic.remove(panelDraw.getSelectedIndex());
    }

    public void deleteHistoric(int i) {
        historic.remove(i);
    }

    public void deleteAllHistoric() {
        this.historic = new ArrayList<HistoricController>();
    }

    public void deleteTab() {

    }

}