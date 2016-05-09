package GUI.controller.panel;

import GUI.controller.MainController;
import GUI.controller.historic.ActionPanel;
import GUI.controller.historic.HistoricController;
import GUI.model.DrawModel;
import GUI.model.HistoricModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * We give you this class to help you display images.
 * You are free to use it or not, to modify it.
 */
public class ImagePanel extends JPanel implements Serializable, MouseListener, MouseMotionListener {
    private static final long serialVersionUID = -314159265358979323L;
    private String fileName;
    public String path = "";
    public transient int modify;
    private final int width;
    private final int height;
    private final int imageType;
    private final int[] pixels;
    public transient BufferedImage image;
    public transient boolean mouseIn = false;
    public transient boolean dragged = false;
    public HistoricController historic;

    /**
     * Create the ImagePanel
     *
     * @param image: image to display
     * @param name:  name of the image
     */
    public ImagePanel(BufferedImage image, String name) {
        fileName = name;
        this.image = image;
        this.modify = 0;
        width = image.getWidth();
        height = image.getHeight();
        imageType = image.getType();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
        addMouseMotionListener(this);
        addMouseListener(this);
    }


    /**
     * Create the ImagePanel
     *
     * @param file: image to display
     */
    public ImagePanel(File file) {
        try {
            image = ImageIO.read(file);
            fileName = file.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        width = image.getWidth();
        height = image.getHeight();
        imageType = image.getType();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    /**
     * Create the bufferImage after deserialization.
     */
    public void buildImage() {
        image = new BufferedImage(width, height, imageType);
        image.setRGB(0, 0, width, height, pixels, 0, width);

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.modify = 1;
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setHistoric() {
        this.historic = HistoricModel.getInstance().getHistoric();
    }

    public HistoricController getHistoric() {
        return historic;
    }



    @Override
    public void mouseMoved(MouseEvent e) {
        if(e.getX() > 0 && e.getX()<image.getWidth() && e.getY() > 0 && e.getY() < image.getHeight())
            mouseIn = true;
        else
            mouseIn = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        DrawModel model = DrawModel.getInstance();
        dragged = true;
        Graphics g = image.getGraphics();
        g.setColor(model.getColor());
        Point p = e.getPoint();
        g.fillOval(p.x, p.y, 5, 5);
        g.dispose();
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!mouseIn || !dragged) return;
        MainController.applyModification(image,new ActionPanel("Draw", image));
        dragged = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }



}
