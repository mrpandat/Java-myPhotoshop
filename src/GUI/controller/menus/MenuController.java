package GUI.controller.menus;

import GUI.controller.panel.ImagePanel;
import GUI.model.HistoricModel;
import GUI.model.MainModel;
import GUI.view.layout.ProjectPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;


public class MenuController {
    public MainModel model = MainModel.getInstance();
    public HistoricModel historic = HistoricModel.getInstance();

    public void performOpen() {
        ArrayList<String> a = new ArrayList<String>();
        a.add(".myPSD");
        a.add(".bmp");
        a.add(".jpg");
        a.add(".png");
        a.add(".gif");
        JFileChooser fc = getFileChooser(a);
        PreviewFileChooser preview = new PreviewFileChooser(fc);
        fc.addPropertyChangeListener(preview);
        fc.setAccessory(preview);
        if (fc.showOpenDialog(model.mainPanel) == JFileChooser.APPROVE_OPTION) {
            for (File file : fc.getSelectedFiles()) {
                if (!file.canRead() || !file.canWrite()) {
                    JOptionPane.showMessageDialog(MainModel.getInstance().mainPanel, "Bad rights for the file " + file.getName());
                }
                openFile(file);
            }
        }

        model.notifyObservers();
    }

    public void openFile(File file) {
        if (file.getName().endsWith(".myPSD")) {
            //OPEN A SERIALIZABLE FILE
            FileInputStream fin = null;
            try {

                fin = new FileInputStream(file.getPath());
                ObjectInputStream ois = new ObjectInputStream(fin);
                ImagePanel img = (ImagePanel) ois.readObject();

                //PREPARE HISTORIC
                img.getHistoric().buildImages();
                ProjectPanel p = new ProjectPanel(new ImagePanel(img.getHistoric().getHistoricImage(), file.getPath()));
                HistoricModel.getInstance().setHistoric(img.getHistoric());

                model.panelDraw.addTab(file.getName(), p.getContent());
                model.panelDraw.setSelectedIndex(model.panelDraw.getTabCount() - 1);
                model.getImg().modify = 0;
                ois.close();
                fin.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ProjectPanel p = new ProjectPanel(file);
                model.panelDraw.addTab(file.getName(), p.getContent());
                model.panelDraw.setSelectedIndex(model.panelDraw.getTabCount() - 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainModel.getInstance().mainPanel, "This file is corrupted, or not at the good format " + file.getName());
            }
        }
    }

    public void createProject() {
        String name = (String) (JOptionPane.showInputDialog(MainModel.getInstance().mainPanel,
                "name of your project :"));
        if(name==null|| name.isEmpty()) return;
        ProjectPanel p = new ProjectPanel(
                new ImagePanel(
                        new BufferedImage(500, 500, 2),
                        name + ".myPSD")
        );
        model.panelDraw.addTab(name + ".myPSD", p.getContent());
        model.panelDraw.setSelectedIndex(model.panelDraw.getTabCount() - 1);
        model.getImg().modify = 0;

    }

    public void performCloseAll() {
        HistoricModel.getInstance().deleteAllHistoric();
        while (model.panelDraw.getTabCount() > 0) {
            performClose();
        }
        model.notifyObservers();
    }

    public void performClose() {
        if (model.panelDraw.getTabCount() > 0) {
            HistoricModel.getInstance().deleteHistoric();
            model.panelDraw.remove(model.panelDraw.getSelectedIndex());
        }
        model.notifyObservers();
    }


    public void performSave() {
        if (model.panelDraw.getTabCount() <= 0) return;
        File f = new File(MainModel.getInstance().getImg().getFileName());
        if (!f.exists()) {
            performSaveAs();
            return;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput oos = null;
        String s = MainModel.getInstance().getImg().getFileName();
        try {
            if (
                    s.endsWith(".bmp") ||
                            s.endsWith(".jpg") ||
                            s.endsWith(".gif") ||
                            s.endsWith(".png")) {
                s = s.substring(0, s.length() - 3) + "myPSD";
            }


            MainModel.getInstance().getImg().setHistoric();
            //get obj as bytes
            oos = new ObjectOutputStream(bos);
            oos.writeObject(MainModel.getInstance().getImg());
            byte[] data = bos.toByteArray();

            //save the bytes
            FileOutputStream out = new FileOutputStream(s);
            out.write(data);
            out.flush();
            oos.flush();
            oos.close();
            out.close();

            model.setStatusBar("Save");
            model.getImg().modify = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void performSaveAs() {
        if (model.panelDraw.getTabCount() <= 0) return;
        ArrayList<String> a = new ArrayList<String>();
        a.add(".myPSD");
        JFileChooser fc = getFileChooser(a);
        fc.setMultiSelectionEnabled(false);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput oos = null;
        if (fc.showSaveDialog(model.mainPanel) == JFileChooser.APPROVE_OPTION) {
            try {

                MainModel.getInstance().getImg().setHistoric();
                //get obj as bytes
                oos = new ObjectOutputStream(bos);
                oos.writeObject(MainModel.getInstance().getImg());
                byte[] data = bos.toByteArray();

                //save the bytes
                FileOutputStream out = new FileOutputStream(fc.getSelectedFile().getPath() + ".myPSD");
                out.write(data);
                out.flush();
                oos.flush();
                oos.close();
                out.close();

                model.setStatusBar("Save as");
                model.getImg().setName(fc.getSelectedFile().getName() + ".myPSD");
                model.getImg().modify = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public JFileChooser getFileChooser(ArrayList<String> filters) {
        JFileChooser fc = new JFileChooser("~");
        fc.removeChoosableFileFilter(fc.getFileFilter());
        fc.setMultiSelectionEnabled(true);
        fc.setFileFilter(new FileFilter() {
                             public boolean accept(File f) {
                                 if (f.getName().length() < 5 && !f.getName().contains("."))
                                     return false;
                                 if (f.isDirectory()) {
                                     return true;
                                 } else if (
                                         filters.contains(f.getName().substring(f.getName().length() - 4, f.getName().length()))) {
                                     return true;
                                 } else if (
                                         filters.contains(f.getName().substring(f.getName().length() - 6, f.getName().length()))) {
                                     return true;
                                 } else return false;
                             }

                             public String getDescription() {
                                 return "Images Files";
                             }
                         }
        );

        return fc;
    }


    @Deprecated
    public void performOpenFromClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {

            BufferedImage image = (BufferedImage) clipboard.getData(DataFlavor.imageFlavor);
            String name = (String) (JOptionPane.showInputDialog(MainModel.getInstance().mainPanel,
                    "name of your project :"));
            if(name==null|| name.isEmpty()) return;
            ProjectPanel p = new ProjectPanel(
                    new ImagePanel(
                            image,
                            name + ".myPSD")
            );
            model.panelDraw.addTab(name + ".myPSD", p.getContent());
            model.panelDraw.setSelectedIndex(model.panelDraw.getTabCount() - 1);
        } catch (UnsupportedFlavorException ufe) {
            ufe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
