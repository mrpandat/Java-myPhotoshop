package GUI.controller.panel;

import GUI.controller.historic.ActionPanel;
import GUI.controller.historic.HistoricController;
import GUI.model.MainModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * We give you this class to help you display images.
 * You are free to use it or not, to modify it.
 */
public class ImagePanel extends JPanel implements Serializable
{
    private static final long serialVersionUID = -314159265358979323L;
    private String fileName;
    private final int width;
    private final int height;
    private final int imageType;
    private final int[] pixels;
    public transient BufferedImage image;
    public HistoricController historic;

    /**
     * Create the ImagePanel
     *
     * @param image: image to display
     * @param name: name of the image
     */
    public ImagePanel(BufferedImage image, String name)
    {
        fileName = name;
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        imageType = image.getType();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }



    /**
     * Create the ImagePanel
     *
     * @param file: image to display
     */
    public ImagePanel(File file)
    {
        try
        {
            image = ImageIO.read(file);
            fileName = file.getPath();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        width = image.getWidth();
        height = image.getHeight();
        imageType = image.getType();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    /**
     * Create the bufferImage after deserialization.
     */
    public void buildImage()
    {
        image = new BufferedImage(width, height, imageType);
        image.setRGB(0, 0, width, height, pixels, 0, width);
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public void setImage(BufferedImage image)
    {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public void setHistoric() {
        this.historic = MainModel.getInstance().getHistoric();
    }

    public HistoricController getHistoric() {
        return historic;
    }
}
