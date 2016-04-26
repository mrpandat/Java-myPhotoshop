package GUI.controller;

import GUI.model.MainModel;
import GUI.view.layout.ImagePanel;
import GUI.view.layout.ProjectPanel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by pandat on 24/04/2016.
 */
public class MenuController {
    public MainModel model = MainModel.getInstance();

    public void performOpen() {
        JFileChooser fc = new JFileChooser("~");
        fc.removeChoosableFileFilter(fc.getFileFilter() );
        fc.setMultiSelectionEnabled(true);
        fc.setFileFilter(new FileFilter() {
                                      public boolean accept(File f) {
                                          if (f.isDirectory()) {
                                              return true;
                                          } else if (
                                                  f.getName().endsWith(".bmp") ||
                                                          f.getName().endsWith(".jpg") ||
                                                          f.getName().endsWith(".gif") ||
                                                          f.getName().endsWith(".png")) {
                                              return true;
                                          } else return false;
                                      }

                                      public String getDescription() {
                                          return "Images Files";
                                      }
                                  }
        );
        if (fc.showOpenDialog(model.mainPanel) == JFileChooser.APPROVE_OPTION) {
            for (File file : fc.getSelectedFiles()) {
                ProjectPanel p = new ProjectPanel(file);
                model.panelDraw.addTab(file.getName(), p.getContent());
                model.panelDraw.setSelectedIndex(model.panelDraw.getTabCount() - 1);
            }

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
