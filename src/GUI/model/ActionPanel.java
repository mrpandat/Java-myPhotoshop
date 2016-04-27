package GUI.model;

import java.awt.image.BufferedImage;

/**
 * Created by jean on 27/04/2016.
 */
public class ActionPanel {
    public String name;



    public BufferedImage img;

    public ActionPanel(String name,BufferedImage img) {
        this.name = name;
        this.img = img;
    }
    public BufferedImage getImg() {
        return img;
    }
    public String getName() {
        return name;
    }
}
