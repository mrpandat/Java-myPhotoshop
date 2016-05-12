package GUI.view.layout;

import GUI.controller.MainController;
import GUI.model.HistoricModel;
import GUI.model.MainModel;
import filter.Filter;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static javax.swing.KeyStroke.getKeyStroke;


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
        file.setIcon(new ImageIcon("asset/file.png"));

        this.add(file);

        JMenuItem mi = new JMenuItem();
        mi.setText("Create");
        mi.setIcon(new ImageIcon("asset/new.png"));
        file.add(mi);
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));

        mi.addActionListener(e -> controller.menuController.createProject());

        mi = new JMenuItem();
        mi.setText("Open");
        mi.setIcon(new ImageIcon("asset/open.png"));

        file.add(mi);
        fc = new JFileChooser("~");
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));

        mi.addActionListener(e -> controller.menuController.performOpen());

        mi = new JMenuItem();
        mi.setText("Open from clipboard");
        mi.setIcon(new ImageIcon("asset/clipboard.png"));

        file.add(mi);
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
        mi.addActionListener(e -> controller.menuController.performOpenFromClipboard());

        mi = new JMenuItem();
        mi.setText("Save");
        mi.setIcon(new ImageIcon("asset/save.png"));
        file.add(mi);
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));

        mi.addActionListener(e -> controller.menuController.performSave());

        mi = new JMenuItem();
        mi.setText("Save As..");
        mi.setIcon(new ImageIcon("asset/saveas.png"));
        file.add(mi);
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
        mi.addActionListener(e -> controller.menuController.performSaveAs());



        mi = new JMenuItem();
        mi.setText("Close");
        mi.setIcon(new ImageIcon("asset/close.png"));

        file.add(mi);
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));

        mi.addActionListener(e -> controller.menuController.performClose());


        mi = new JMenuItem();
        mi.setText("Close All");
        mi.setIcon(new ImageIcon("asset/closeall.png"));

        file.add(mi);

        mi.addActionListener(e -> controller.menuController.performCloseAll());
    }

    private void generateEditMenu(MainController controller) {
        /****** EDIT MENU *****/

        JMenu edit = new JMenu();
        edit.setIcon(new ImageIcon("asset/edit.png"));

        edit.setText("Edit");
        this.add(edit);

        JMenuItem mi;

        mi = new JMenuItem();
        mi.setText("Undo");
        mi.setIcon(new ImageIcon("asset/undo.png"));
        mi.addActionListener(e -> HistoricModel.getInstance().undo());
        mi.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK)));
        edit.add(mi);

        mi = new JMenuItem();
        mi.setText("Redo");
        mi.setIcon(new ImageIcon("asset/redo.png"));
        mi.addActionListener(e -> HistoricModel.getInstance().redo());
        mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
        edit.add(mi);

        mi = new JMenuItem();
        mi.setText("Cancel Filter");
        mi.setIcon(new ImageIcon("asset/cancel.png"));
        mi.addActionListener(e -> MainModel.getInstance().cancelFilter());
        edit.add(mi);

        mi = new JMenuItem();
        mi.setText("Change historic size");
        mi.setIcon(new ImageIcon("asset/historic.png"));

        mi.addActionListener(e -> HistoricModel.getInstance().setSize());
        edit.add(mi);


    }


    private void generateFiltersMenu(MainController controller) {
        /****** FILTERS MENU *****/


        JMenu filters = new JMenu();
        filters.setText("Filters");
        filters.setIcon(new ImageIcon("asset/filter.png"));

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
