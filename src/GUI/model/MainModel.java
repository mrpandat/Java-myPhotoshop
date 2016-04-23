package GUI.model;

import GUI.view.layout.ImagePanel;
import GUI.view.layout.MyLayout;

import javax.swing.*;
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


    public void performOpen() {
        JFileChooser fc = new JFileChooser("~");
        if (fc.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
            panelDraw.addTab(fc.getSelectedFile().getName(), new ImagePanel(fc.getSelectedFile()));
            panelDraw.setSelectedIndex(panelDraw.getTabCount() - 1);
        }
        notifyObservers();
    }
}