package GUI.controller.menus;

import GUI.controller.historic.ActionPanel;
import GUI.controller.panel.ImagePanel;
import GUI.model.MainModel;
import GUI.view.layout.ProjectPanel;
import main.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;


public class MenuController {
    public MainModel model = MainModel.getInstance();

    public void performOpen() {
        ArrayList<String> a = new ArrayList<String>();
        a.add(".myPSD");
        a.add(".bmp");
        a.add(".jpg");
        a.add(".png");
        a.add(".gif");
        JFileChooser fc = getFileChooser(a);
        if (fc.showOpenDialog(model.mainPanel) == JFileChooser.APPROVE_OPTION) {
            for (File file : fc.getSelectedFiles()) {
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
                        MainModel.getInstance().setHistoric(img.getHistoric());

                        model.panelDraw.addTab(file.getName(), p.getContent());
                        model.panelDraw.setSelectedIndex(model.panelDraw.getTabCount() - 1);
                        ois.close();
                        fin.close();
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
                    model.deleteHistoric(i);
                    model.panelDraw.removeTabAt(i);
                }
                i++;
            }
        }
        model.notifyObservers();

    }

    public void performCloseAll() {
        model.deleteAllHistoric();
        if (model.panelDraw.getTabCount() > 0) {
            model.panelDraw.removeAll();
        }
        model.notifyObservers();
    }

    public void performClose() {
        if (model.panelDraw.getTabCount() > 0) {
            model.deleteHistoric();
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


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void performSaveAs() {
         ArrayList<String> a = new ArrayList<String>();
        a.add(".myPSD");
        JFileChooser fc = getFileChooser(a);
        fc.setMultiSelectionEnabled(false);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput oos = null;
        if (fc.showOpenDialog(model.mainPanel) == JFileChooser.APPROVE_OPTION) {
            try {

                MainModel.getInstance().getImg().setHistoric();
                //get obj as bytes
                oos = new ObjectOutputStream(bos);
                oos.writeObject(MainModel.getInstance().getImg());
                byte[] data = bos.toByteArray();

                //save the bytes
                FileOutputStream out = new FileOutputStream(fc.getSelectedFile().getPath());
                out.write(data);
                out.flush();
                oos.flush();
                oos.close();
                out.close();


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
                                 if (f.isDirectory()) {
                                     return true;
                                 } else if (
                                         filters.contains(f.getName().substring(f.getName().length() - 4, f.getName().length()))) {
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

}
