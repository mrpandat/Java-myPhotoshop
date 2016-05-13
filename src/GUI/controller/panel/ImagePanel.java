package GUI.controller.panel;

import GUI.controller.MainController;
import GUI.controller.historic.ActionPanel;
import GUI.controller.historic.HistoricController;
import GUI.model.DrawModel;
import GUI.model.HistoricModel;
import GUI.model.MainModel;
import main.Main;

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
import java.util.ArrayList;

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
        if (e.getX() > 0 && e.getX() < image.getWidth() && e.getY() > 0 && e.getY() < image.getHeight())
            mouseIn = true;
        else
            mouseIn = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DrawModel model = DrawModel.getInstance();
        if (model.getType().equals("bucket")) bucketFill(e.getPoint());
        if (!model.getType().equals("polygon")) return;
        if (model.getShape().equals("Polygon")) {
            model.addPoint(e.getPoint());
            if (model.getNbshape() == model.getClickPoints().size())
                drawPolygon();
            return;
        }
        //if polygon is not selected, reset all point
        if (model.getClickPoints().size() > 1) model.resetPoint();
        if (model.getClickPoints().isEmpty()) {
            model.addPoint(e.getPoint());
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(model.getColor());
        drawRectangle(e.getPoint(), model.getClickPoints().get(0), g);
        g.dispose();
        repaint();
        model.resetPoint();
        MainController.applyModification(image, new ActionPanel(DrawModel.getInstance().getType(), image));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        DrawModel model = DrawModel.getInstance();
        dragged = true;
        Graphics g = image.getGraphics();
        g.setColor(model.getColor());
        Point p = e.getPoint();
        switch (model.getType()) {
            case "draw":
                if (model.getShape().equals("Oval"))
                    g.fillOval(p.x, p.y, model.getSize(), model.getSize());
                else if (model.getShape().equals("Square"))
                    g.fillRect(p.x, p.y, model.getSize(), model.getSize());
                else if (model.getShape().equals("Rectangle"))
                    g.fillRect(p.x, p.y, model.getSize() * 2, model.getSize());
                break;
            case "erase":

                g.setColor(new Color(255, 255, 255, model.getOpacity()));
                g.fillOval(p.x, p.y, model.getSize(), model.getSize());
                break;
        }
        g.dispose();
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!mouseIn || !dragged || DrawModel.getInstance().getShape().isEmpty()) return;
        MainController.applyModification(image, new ActionPanel(DrawModel.getInstance().getType(), image));
        dragged = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void drawRectangle(Point p1, Point p2, Graphics g) {
        Rectangle rect = new Rectangle(p1);
        rect.add(p2);
        DrawModel model = DrawModel.getInstance();
        switch (model.getShape()) {
            case "Rectangle":
                g.fillRoundRect(rect.x, rect.y, rect.width, rect.height, model.getSize(), model.getSize());
                break;
            case "Oval":
                g.fillOval(rect.x, rect.y, rect.width, rect.height);
                break;

        }
    }

    public void drawPolygon() {
        DrawModel drawModel = DrawModel.getInstance();
        Graphics g = image.getGraphics();
        g.setColor(drawModel.getColor());


        int[] xpoint = new int[drawModel.getNbshape()];
        int[] ypoint = new int[drawModel.getNbshape()];

        for (int i = 0; i < drawModel.getNbshape(); i++) {
            xpoint[i] = (int) drawModel.getClickPoints().get(i).getX();
            ypoint[i] = (int) drawModel.getClickPoints().get(i).getY();
        }

        Polygon p = new Polygon(xpoint, ypoint, drawModel.getNbshape());
        g.fillPolygon(p);
        g.dispose();
        repaint();
        drawModel.resetPoint();
        MainController.applyModification(image, new ActionPanel(DrawModel.getInstance().getType(), image));
        return;
    }

    public void bucketFill(Point p) {
        DrawModel drawModel = DrawModel.getInstance();
        BufferedImage img = MainModel.getInstance().getImg().getImage();
        ArrayList<Point> a = new ArrayList<Point>();
        fillPixels(img, img.getRGB(p.x, p.y), p, a);
        for (Point point : a) {
            img.setRGB(point.x, point.y, Color.red.getRGB());
        }
        repaint();
        drawModel.resetPoint();
        MainController.applyModification(image, new ActionPanel(DrawModel.getInstance().getType(), image));
        return;
    }

    public void fillPixels(BufferedImage img, int color, Point p, ArrayList<Point> a) {
        if (
                p.x <= 0 || p.y <= 0
                        || p.x >= img.getWidth() - 1 || p.y >= img.getHeight() - 1
                        || img.getRGB(p.x, p.y) != color
                )
            return;
        if (!a.contains(p))
            a.add(p);
        else
            return;
        if (img.getRGB(p.x + 1, p.y) == color) {
            fillPixels(img, color, new Point(p.x + 1, p.y), a);
        }
        if (img.getRGB(p.x, p.y + 1) == color) {
            fillPixels(img, color, new Point(p.x, p.y + 1), a);
        }
        if (img.getRGB(p.x - 1, p.y) == color) {
            fillPixels(img, color, new Point(p.x - 1, p.y), a);
        }
        if (img.getRGB(p.x, p.y - 1) == color) {
            fillPixels(img, color, new Point(p.x, p.y - 1), a);
        }
    }


}
