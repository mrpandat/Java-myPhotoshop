package GUI.model;

import GUI.view.layout.ImagePanel;
import javax.swing.*;
import java.awt.image.BufferedImage;
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

    public ImagePanel getImg() {
        return (ImagePanel)((JPanel)this.panelDraw.getSelectedComponent()).getComponents()[0];
    }

    public void setImg(BufferedImage img) {
        getImg().setImage(img);
        notifyObservers();
        setChanged();
    }

}