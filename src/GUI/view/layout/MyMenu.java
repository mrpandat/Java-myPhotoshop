package GUI.view.layout;

import GUI.controller.MainController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class MyMenu extends JMenuBar {
    public JPanel mainPanel;
    public JTabbedPane panelDraw;
    public JFileChooser fc;

    MyMenu(JPanel mainPanel, JTabbedPane panelDraw) {
        this.mainPanel = mainPanel;
        this.panelDraw = panelDraw;
        this.fc = new JFileChooser();
    }

    public void generateMenus(MainController controller) {
        generateFileMenu(controller);
        generateEditMenu(controller);
        generateFiltersMenu(controller);
    }
    private void generateFileMenu(MainController controller) {

        /****** FILE MENU *****/
        JMenu file = new JMenu();

        file.setText("File");
        file.setMnemonic(KeyEvent.VK_F);
        this.add(file);

        JMenuItem mi = new JMenuItem();
        mi.setText("Open");
        mi.setMnemonic(KeyEvent.VK_N);
        file.add(mi);
        fc = new JFileChooser("~");

        mi.addActionListener(
                controller/*
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (fc.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                            panelDraw.addTab(fc.getSelectedFile().getName(), new ImagePanel(fc.getSelectedFile()));
                            panelDraw.setSelectedIndex(panelDraw.getTabCount() - 1);
                        }
                    }
                }*/
        );

        mi = new JMenuItem();
        mi.setText("Save");
        mi.setMnemonic(KeyEvent.VK_S);
        file.add(mi);

        mi = new JMenuItem();
        mi.setText("Save As..");
        mi.setMnemonic(KeyEvent.VK_D);
        file.add(mi);

        mi = new JMenuItem();
        mi.setText("Close");
        mi.setMnemonic(KeyEvent.VK_C);
        file.add(mi);

        mi.addActionListener(
                controller/*
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (panelDraw.getTabCount() > 0) {
                            panelDraw.remove(panelDraw.getSelectedIndex());
                        }
                    }
                }*/
        );


        mi = new JMenuItem();
        mi.setText("Close others");
        mi.setMnemonic(KeyEvent.VK_O);
        file.add(mi);

        mi.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (panelDraw.getTabCount() > 0) {
                            int i = 0;
                            while (panelDraw.getTabCount() != 1) {
                                if (panelDraw.getSelectedIndex() != i) {
                                    panelDraw.removeTabAt(i);
                                }
                                i++;
                            }
                        }
                    }
                }
        );

        mi = new JMenuItem();
        mi.setText("Close All");
        mi.setMnemonic(KeyEvent.VK_C);
        file.add(mi);

        mi.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (panelDraw.getTabCount() > 0) {
                            panelDraw.removeAll();

                        }
                    }
                }
        );
        return;
    }

    private void generateEditMenu(MainController controller) {
        /****** EDIT MENU *****/

        JMenu edit = new JMenu();
        edit.setText("Edit");
        this.add(edit);

        JMenuItem mi = new JMenuItem();
        mi.setText("Undo");
        mi.setMnemonic(KeyEvent.VK_U);
        edit.add(mi);

        mi = new JMenuItem();
        mi.setText("Redo..");
        mi.setMnemonic(KeyEvent.VK_R);
        edit.add(mi);

        mi = new JMenuItem();
        mi.setText("Add Text..");
        edit.add(mi);
    }

    private void generateFiltersMenu(MainController controller) {
        /****** FILTERS MENU *****/

        JMenu filters = new JMenu();
        filters.setText("Filters");
        filters.setMnemonic(KeyEvent.VK_I);
        this.add(filters);

        JMenuItem mi = new JMenuItem();
        mi.setText("My Filter");
        filters.add(mi);
    }
}
