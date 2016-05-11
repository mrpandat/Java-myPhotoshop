package GUI.controller.menus;

import GUI.controller.panel.ImagePanel;
import GUI.model.HistoricModel;
import GUI.model.MainModel;
import GUI.view.layout.ProjectPanel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


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

                file = performUncompress(file);
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
                model.getImg().path = file.getPath();
                ois.close();
                fin.close();
                file.delete();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ProjectPanel p = new ProjectPanel(file);
                model.panelDraw.addTab(file.getName(), p.getContent());
                model.panelDraw.setSelectedIndex(model.panelDraw.getTabCount() - 1);
                model.getImg().path = file.getPath().substring(0, file.getPath().length() - 4) + ".myPSD";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(MainModel.getInstance().mainPanel, "This file is corrupted, or not at the good format " + file.getName());
            }
        }
    }

    public void createProject() {
        String name = (String) (JOptionPane.showInputDialog(MainModel.getInstance().mainPanel,
                "name of your project :"));
        if (name == null || name.isEmpty()) return;
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
        File f = new File(MainModel.getInstance().getImg().path);
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
            s += ".temp";
            FileOutputStream out = new FileOutputStream(s);
            out.write(data);
            out.flush();
            oos.flush();
            oos.close();
            out.close();

            performCompress(new File(s), model.getImg().path , f.getName());

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

                ImagePanel imgp = model.getImg();
                imgp.setHistoric();
                imgp.path = fc.getSelectedFile().getPath();
                if (!fc.getSelectedFile().getPath().endsWith(".myPSD"))
                    imgp.path += ".myPSD";
                imgp.setName(fc.getName());

                //get obj as bytes
                oos = new ObjectOutputStream(bos);
                oos.writeObject(imgp);
                byte[] data = bos.toByteArray();

                //save the bytes
                FileOutputStream out;
                String fpath;
                String fname;
                if (fc.getSelectedFile().getPath().endsWith(".myPSD")) {
                    fpath = (fc.getSelectedFile().getPath());
                    fname = (fc.getSelectedFile().getName());

                } else {
                    fpath = (fc.getSelectedFile().getPath() + ".myPSD");
                    fname = (fc.getSelectedFile().getName() + ".myPSD");

                }

                out = new FileOutputStream(fpath + ".temp");

                //closing
                out.write(data);
                out.flush();
                oos.flush();
                oos.close();
                out.close();


                performCompress(new File(fpath + ".temp"), fpath, fname);
                model.setStatusBar("Save as");
                model.getImg().setName(fc.getSelectedFile().getName() + ".myPSD");
                model.getImg().modify = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void performCompress(File temp, String fpath, String fname) {

        byte[] buffer = new byte[1024];

        try {

            FileOutputStream fos = new FileOutputStream(fpath);
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze = new ZipEntry(fname);

            zos.putNextEntry(ze);
            FileInputStream in = new FileInputStream(temp.getPath());
            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            in.close();
            zos.closeEntry();
            zos.close();
            temp.delete();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static File performUncompress(File f) {

        byte[] buffer = new byte[1024];
        File newFile = new File("");
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(f));
            ZipEntry ze = zis.getNextEntry();

            if (ze != null) {

                String fileName = ze.getName();
                newFile = new File(fileName);
                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
            }

            zis.closeEntry();
            zis.close();

            return newFile;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return newFile;
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
            if (name == null || name.isEmpty()) return;
            ProjectPanel p = new ProjectPanel(
                    new ImagePanel(
                            image,
                            name + ".myPSD")
            );
            model.panelDraw.addTab(name + ".myPSD", p.getContent());
            model.panelDraw.setSelectedIndex(model.panelDraw.getTabCount() - 1);
        } catch (UnsupportedFlavorException e) {
            JOptionPane.showMessageDialog(MainModel.getInstance().mainPanel,
                    "Your clipboard must have an image in it");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
