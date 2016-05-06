package GUI.view.layout;

import GUI.controller.MainController;
import GUI.model.HistoricModel;
import GUI.model.MainModel;
import filter.Filter;

import javax.swing.*;
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
        mi.setText("Create");
        file.add(mi);
        mi.addActionListener(e -> controller.menuController.createProject());

        mi = new JMenuItem();
        mi.setText("Open");
        mi.setMnemonic(KeyEvent.VK_N);
        file.add(mi);
        fc = new JFileChooser("~");

        mi.addActionListener(e -> controller.menuController.performOpen());

        mi = new JMenuItem();
        mi.setText("Save");
        mi.setMnemonic(KeyEvent.VK_S);
        file.add(mi);
        mi.addActionListener(e -> controller.menuController.performSave());

        mi = new JMenuItem();
        mi.setText("Save As..");
        mi.setMnemonic(KeyEvent.VK_D);
        file.add(mi);
        mi.addActionListener(e -> controller.menuController.performSaveAs());



        mi = new JMenuItem();
        mi.setText("Close");
        mi.setMnemonic(KeyEvent.VK_C);
        file.add(mi);
        mi.addActionListener(e -> controller.menuController.performClose());


        mi = new JMenuItem();
        mi.setText("Close All");
        mi.setMnemonic(KeyEvent.VK_C);
        file.add(mi);

        mi.addActionListener(e -> controller.menuController.performCloseAll());
    }

    private void generateEditMenu(MainController controller) {
        /****** EDIT MENU *****/

        JMenu edit = new JMenu();
        edit.setText("Edit");
        this.add(edit);

        JMenuItem mi;

        mi = new JMenuItem();
        mi.setText("Undo");
        mi.addActionListener(e -> HistoricModel.getInstance().undo());
        edit.add(mi);

        mi = new JMenuItem();
        mi.setText("Redo");
        mi.addActionListener(e -> HistoricModel.getInstance().redo());
        edit.add(mi);

        mi = new JMenuItem();
        mi.setText("Cancel Filter");
        mi.addActionListener(e -> MainModel.getInstance().cancelFilter());
        edit.add(mi);

        mi = new JMenuItem();
        mi.setText("Change historic size");
        mi.addActionListener(e -> HistoricModel.getInstance().setSize());
        edit.add(mi);


    }


    private void generateFiltersMenu(MainController controller) {
        /****** FILTERS MENU *****/


        JMenu filters = new JMenu();
        filters.setText("Filters");
        filters.setMnemonic(KeyEvent.VK_I);
        this.add(filters);

        for (Class aClass : controller.filtersController.getClasses()) {
            JMenuItem mi = new JMenuItem();
            try {
                Filter filter = (Filter)aClass.newInstance();
                mi.setText(filter.getName());
                mi.addActionListener(e -> controller.applyFilter(filter));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            filters.add(mi);
        }

    }
}
