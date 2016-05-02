package GUI.controller.historic;

import GUI.controller.panel.ImagePanel;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by jean on 27/04/2016.
 */
public class ActionPanel implements Serializable{
    public String name;
    public ImagePanel imgp;
    public transient BufferedImage img;

    public ActionPanel(String name,BufferedImage img) {
        this.name = name;
        this.img = img;
        this.imgp = new ImagePanel(img, name);
    }

    public void rebuildImages() {
        this.imgp.buildImage();
        this.img = this.imgp.getImage();
    }
    public BufferedImage getImg() {
        return img;
    }
    public String getName() {
        return name;
    }
}
