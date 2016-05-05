package GUI.model;

import GUI.controller.historic.ActionPanel;

import GUI.controller.panel.ImagePanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;

@SuppressWarnings("unchecked")
/**
 * This is the main model of the application, this is a singleton, so everyone can access it
 * whenever it is needed
 */
public class MainModel extends Observable {

    public JPanel mainPanel;
    public JTabbedPane panelDraw;
    public JLabel statusBar;
    public ArrayList<Thread> filterThread = new ArrayList<Thread>();

    private MainModel() {
        this.mainPanel = new JPanel();
        this.panelDraw = new JTabbedPane();
        this.statusBar = new JLabel("Welcome to myphotoshop :)");
        addListeners();
    }

    private static MainModel INSTANCE = new MainModel();

    public static MainModel getInstance() {
        return INSTANCE;
    }

    /**
     *
     * @return the current image
     */
    public ImagePanel getImg() {
        return (ImagePanel) ((JPanel) this.panelDraw.getSelectedComponent()).getComponents()[0];
    }

    /**
     * Set the image of the current panel and add an entry to the history
     * @param img image of the panel
     * @param action the action applied
     */
    public void setImg(BufferedImage img, ActionPanel action) {
        if (HistoricModel.getInstance().getHistoric().isEmpty()) return;
        if (this.panelDraw.getTabCount() <= 0) return;
        HistoricModel.getInstance().getHistoric().add(action);

        setPrivateImg(img);
    }

    @SuppressWarnings( "deprecation" )
    public void cancelFilter() {
        if (filterThread.isEmpty()) return;
        this.filterThread.get(this.filterThread.size() - 1).stop();
        this.filterThread.remove(this.filterThread.size() - 1);
    }

    /**
     * Set the image panel but does not add an entry to the history
     * @param img
     */
    public void setPrivateImg(BufferedImage img) {
        if (HistoricModel.getInstance().getHistoric().isEmpty()) return;
        if (this.panelDraw.getTabCount() <= 0) return;
        getImg().setImage(img);
        notifyObservers();
        setChanged();

        statusBar.setText(HistoricModel.getInstance().getHistoric().getLastHistoricName());
        HistoricModel.getInstance().setHistoric();
    }

    /**
     * Add the listeners
     */
    private void addListeners() {
        panelDraw.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (panelDraw.getTabCount() <= 0) {
                    statusBar.setText("");
                    HistoricModel.getInstance().historicList.setListData(new Vector());
                    return;
                }
                statusBar.setText(HistoricModel.getInstance().getHistoric().getLastHistoricName());
                HistoricModel.getInstance().setHistoric();
            }
        });


    }


    //STATUS
    public void setStatusBar(String text) {
        this.statusBar.setText(text);
    }

    public String getStatusBar() {
        return this.statusBar.getText();
    }

}