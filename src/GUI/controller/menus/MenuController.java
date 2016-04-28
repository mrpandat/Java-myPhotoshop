package GUI.controller.menus;

import GUI.controller.panel.ImagePanel;
import GUI.model.MainModel;
import GUI.view.layout.ProjectPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;


public class MenuController {
    public MainModel model = MainModel.getInstance();

    public void performOpen() {
        JFileChooser fc = new JFileChooser("~");
        fc.removeChoosableFileFilter(fc.getFileFilter());
        fc.setMultiSelectionEnabled(true);
        fc.setFileFilter(new FileFilter() {
                             public boolean accept(File f) {
                                 if (f.isDirectory()) {
                                     return true;
                                 } else if (
                                         f.getName().endsWith(".bmp") ||
                                                 f.getName().endsWith(".jpg") ||
                                                 f.getName().endsWith(".gif") ||
                                                 f.getName().endsWith(".myPSD") ||
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
                if(file.getName().endsWith(".myPSD")) {
                    //OPEN A SERIALIZABLE FILE
                    FileInputStream fin = null;
                    try {
                        fin = new FileInputStream(file.getPath());
                        ObjectInputStream ois = new ObjectInputStream(fin);
                        ImagePanel img = (ImagePanel) ois.readObject();
                        ProjectPanel p = new ProjectPanel(img);
                        model.panelDraw.addTab(file.getName(), p.getContent());
                        model.panelDraw.setSelectedIndex(model.panelDraw.getTabCount() - 1);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    ProjectPanel p = new ProjectPanel(file);
                    model.panelDraw.addTab(file.getName(), p.getContent());
                    model.panelDraw.setSelectedIndex(model.panelDraw.getTabCount() - 1);
                }
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


    public void performSave() {
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

            //get obj as bytes
            oos = new ObjectOutputStream(bos);
            oos.writeObject(MainModel.getInstance().getImg());
            byte[] data = bos.toByteArray();

            //save the bytes
            FileOutputStream out = new FileOutputStream(s);
            out.write(data);

            oos.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void performSaveAs() {
        JFileChooser fc = new JFileChooser("~");

        try {
            try (FileWriter fw = new FileWriter(fc.getSelectedFile())) {
                File outputfile = fc.getSelectedFile();
                BufferedImage bi = MainModel.getInstance().getImg().getImage();

                boolean b = ImageIO.write(bi, "JPG", outputfile);
            }
        } catch (IOException e) {

        }
    }


}
