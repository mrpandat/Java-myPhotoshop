package GUI.model;

import GUI.view.layout.ImagePanel;
import GUI.view.layout.MyLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;


public class MainModel extends Observable {

    public JPanel mainPanel;
    public JTabbedPane panelDraw;

    private MainModel() {
        this.mainPanel = new JPanel();
        this.panelDraw = new JTabbedPane();
    }

    private static MainModel INSTANCE = new MainModel();

    public static MainModel getInstance() {
        return INSTANCE;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JTabbedPane getPanelDraw() {
        return panelDraw;
    }

    public void setPanelDraw(JTabbedPane panelDraw) {
        this.panelDraw = panelDraw;
    }

    public ImagePanel getImg() {
        return (ImagePanel)this.panelDraw.getComponentAt(panelDraw.getSelectedIndex());
    }

    public void setImg(ImagePanel img) {
        this.panelDraw.setComponentAt(panelDraw.getSelectedIndex(), img);
    }

}