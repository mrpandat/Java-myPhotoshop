package GUI.controller.menus;

import GUI.controller.historic.HistoricController;
import GUI.controller.panel.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PreviewFileChooser extends JPanel implements PropertyChangeListener {
    private JFileChooser jfc;
    private Image img;

    public PreviewFileChooser(JFileChooser jfc) {
        this.jfc = jfc;
        Dimension dimension = new Dimension(350, 350);
        setPreferredSize(dimension);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        try {
            File file = jfc.getSelectedFile();
            updateImage(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateImage(File file) throws IOException {
        if (file == null) {
            img = null;
        } else if (file.getName().endsWith(".myPSD")) {
            img = myFileToImage(file);
        } else if (
                file.getName().endsWith(".bmp")
                        || file.getName().endsWith(".jpg")
                        || file.getName().endsWith(".png")
                        || file.getName().endsWith(".gif")) {
            img = ImageIO.read(file);
        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(110, 110, 110));

        g.fillRect(0, 0, getWidth(), getHeight());
        if (img == null) {
            g.setColor(Color.black);
            g.drawImage(null, 0, 0, 0, 0, null);
            g.drawString("This file is not an image", 42, 100);

        } else {
            int height = img.getHeight(null);
            int width = img.getWidth(null);
            if (height > 350 || width > 350) {
                if (width > height) {
                    float ratio = (100 * 350) / width;
                    width = 350;
                    height = (int) (height * ratio) / 100;
                } else if (height > width) {
                    float ratio = (100 * 350) / height;
                    height = 350;
                    width = (int) (width * ratio) / 100;
                } else {
                    height = 350;
                    width = 350;
                }
            }
            g.drawImage(img, 0, 0, width, height, null);
        }
    }


    public Image myFileToImage(File file) {
        FileInputStream fin = null;
        ImagePanel img = null;
        Image res = null;
        try {
            fin = new FileInputStream(file.getPath());
            ObjectInputStream ois = new ObjectInputStream(fin);
            img = (ImagePanel) ois.readObject();
            img.historic.buildImages();
            res = img.historic.getLastHistoricImage();
            ois.close();
            fin.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
}