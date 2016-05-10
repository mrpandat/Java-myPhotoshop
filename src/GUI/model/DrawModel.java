package GUI.model;

import java.awt.*;

/**
 * Created by jean on 09/05/2016.
 */
public class DrawModel {


    int size = 5;
    int opacity = 50;
    Color color = Color.white;
    String type = "";

    String shape = "";

    private static DrawModel INSTANCE = new DrawModel();

    public static DrawModel getInstance() {
        return INSTANCE;
    }

    private DrawModel() {}

    public void reset() {
         size = 5;
         opacity = 50;
         color = Color.white;
         type = "";
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

}
