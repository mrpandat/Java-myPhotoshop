package GUI.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jean on 09/05/2016.
 */
public class DrawModel {


    private int size = 25;
    private int opacity = 50;
    private int radius = 0;
    private Color color = Color.white;
    private ArrayList<Point> clickPoints = new ArrayList<Point>();
    private String type = "";
    private String shape = "";


    private int nbshape = 5;

    private static DrawModel INSTANCE = new DrawModel();

    public static DrawModel getInstance() {
        return INSTANCE;
    }

    private DrawModel() {
        clickPoints = new ArrayList<Point>();
    }

    public void reset() {
        size = 25;
        opacity = 50;
        color = Color.white;
        type = "";
        clickPoints = new ArrayList<Point>();
        nbshape = 7;
        radius = 0;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public int getOpacity() {
        return opacity;
    }

    public Color getColor() {
        return color;
    }


    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public void addPoint(Point p) {
        clickPoints.add(p);
    }

    public void resetPoint() {
        clickPoints = new ArrayList<Point>();
    }

    public ArrayList<Point> getClickPoints() {
        return clickPoints;
    }

    public int getNbshape() {
        return nbshape;
    }

    public void setNbshape(int nbshape) {
        this.nbshape = nbshape;
    }


    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


}
