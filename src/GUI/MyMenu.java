package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class MyMenu extends JMenuBar {
    public JPanel mainPanel;
    public JFileChooser fc;

    MyMenu(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.fc = new JFileChooser();

        generateFileMenu();
        generateEditMenu();
        generateFiltersMenu();
    }

    private void generateFileMenu() {

        /****** FILE MENU *****/
        JMenu file = new JMenu();

        file.setText("File");
        file.setMnemonic(KeyEvent.VK_F);
        this.add(file);

        JMenuItem mi = new JMenuItem();
        mi.setText("New..");
        mi.setMnemonic(KeyEvent.VK_N);
        file.add(mi);
        fc = new JFileChooser(".");

        mi.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (fc.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {

                        }
                    }
                }
        );

        mi = new JMenuItem();
        mi.setText("Save..");
        mi.setMnemonic(KeyEvent.VK_S);
        file.add(mi);

        mi = new JMenuItem();
        mi.setText("Save As..");
        mi.setMnemonic(KeyEvent.VK_D);
        file.add(mi);
    }

    private void generateEditMenu() {
        /****** EDIT MENU *****/

        JMenu edit = new JMenu();
        edit.setText("Edit");
        edit.setMnemonic(KeyEvent.VK_E);
        this.add(edit);

        JMenuItem mi = new JMenuItem();
        mi.setText("Open..");
        mi.setMnemonic(KeyEvent.VK_O);
        edit.add(mi);

        mi = new JMenuItem();
        mi.setText("Undo..");
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

    private void generateFiltersMenu() {
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
