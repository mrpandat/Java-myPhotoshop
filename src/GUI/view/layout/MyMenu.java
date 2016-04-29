package GUI.view.layout;

import GUI.controller.MainController;
import GUI.model.MainModel;

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
        mi.setText("Close others");
        mi.setMnemonic(KeyEvent.VK_O);
        file.add(mi);

        mi.addActionListener(e -> controller.menuController.performCloseOthers());

        mi = new JMenuItem();
        mi.setText("Close All");
        mi.setMnemonic(KeyEvent.VK_C);
        file.add(mi);

        mi.addActionListener(e -> controller.menuController.performCloseAll());

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
        mi.addActionListener(e -> MainModel.getInstance().undo());
        edit.add(mi);

        mi = new JMenuItem();
        mi.setText("Redo..");
        mi.setMnemonic(KeyEvent.VK_R);
        mi.addActionListener(e -> MainModel.getInstance().redo());

        edit.add(mi);
    }

    private void generateFiltersMenu(MainController controller) {
        /****** FILTERS MENU *****/

        JMenu filters = new JMenu();
        filters.setText("Filters");
        filters.setMnemonic(KeyEvent.VK_I);
        this.add(filters);

        JMenuItem mi = new JMenuItem();
        mi.setText("All blue");
        mi.addActionListener(e -> controller.filtersController.performAllBlue());
        filters.add(mi);

        mi = new JMenuItem();
        mi.setText("Very Long");
        mi.addActionListener(e -> controller.filtersController.performVeryLong());
        filters.add(mi);

        mi = new JMenuItem();
        mi.setText("Rotate Right");
        mi.addActionListener(e -> controller.filtersController.PerformRotateRight());
        filters.add(mi);

        mi = new JMenuItem();
        mi.setText("Rotate Left");
        mi.addActionListener(e -> controller.filtersController.PerformRotateLeft());
        filters.add(mi);

        mi = new JMenuItem();
        mi.setText("Vertical Flip");
        mi.addActionListener(e -> controller.filtersController.PerformVerticalFlip());
        filters.add(mi);

        mi = new JMenuItem();
        mi.setText("Horizontal Flip");
        mi.addActionListener(e -> controller.filtersController.PerformHorizontalFlip());
        filters.add(mi);

        mi = new JMenuItem();
        mi.setText("Grayscale");
        mi.addActionListener(e -> controller.filtersController.PerformGrayscale());
        filters.add(mi);

        mi = new JMenuItem();
        mi.setText("Binary");
        mi.addActionListener(e -> controller.filtersController.PerformBinary());
        filters.add(mi);

        mi = new JMenuItem();
        mi.setText("Grayscale");
        mi.addActionListener(e -> controller.filtersController.PerformGrayscale());
        filters.add(mi);

        mi = new JMenuItem();
        mi.setText("Invert");
        mi.addActionListener(e -> controller.filtersController.PerformInvert());
        filters.add(mi);


    }
}
