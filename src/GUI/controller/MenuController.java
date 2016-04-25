package GUI.controller;

import GUI.model.MainModel;
import GUI.view.layout.ImagePanel;

import javax.swing.*;

/**
 * Created by pandat on 24/04/2016.
 */
public class MenuController {
    public MainModel model = MainModel.getInstance();

    public void performOpen() {
        JFileChooser fc = new JFileChooser("~");
        if (fc.showOpenDialog(model.mainPanel) == JFileChooser.APPROVE_OPTION) {
            model.panelDraw.addTab(fc.getSelectedFile().getName(), new ImagePanel(fc.getSelectedFile()));
            model.panelDraw.setSelectedIndex(model.panelDraw.getTabCount() - 1);
        }

        model.notifyObservers();
    }

    public void performCloseOthers() {
        if (model.panelDraw.getTabCount() > 0) {
            int i = 0;
            while (model.panelDraw.getTabCount() != 1) {
                if (model.panelDraw.getSelectedIndex() != i) {
                    model.panelDraw.removeTabAt(i);
                }
                i++;
            }
        }
        model.notifyObservers();

    }

    public void performCloseAll() {
        if (model.panelDraw.getTabCount() > 0) {
            model.panelDraw.removeAll();
        }
        model.notifyObservers();
    }

    public void performClose() {
        if (model.panelDraw.getTabCount() > 0) {
            model.panelDraw.remove(model.panelDraw.getSelectedIndex());
        }
        model.notifyObservers();
    }


}
