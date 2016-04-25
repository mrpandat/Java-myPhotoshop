package filter.basic;

import filter.Filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Reverse implements Filter {
    @Override
    public BufferedImage perform(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage flippedImage = new BufferedImage(w, h, img.getColorModel().getTransparency());
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
        g.dispose();
        return flippedImage;

    }

    @Override
    public String getName() {
        return "Reverse";
    }
}
