package filter.basic;

import filter.Filter;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class Invert implements Filter {
    @Override
    public BufferedImage perform(BufferedImage img) {


        BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        int width = img.getWidth();
        int height = img.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgba = img.getRGB(i, j);
                Color newColor = new Color(rgba, true);
                newColor = new Color(255 - newColor.getRed(), 255 - newColor.getGreen(), 255 - newColor.getBlue());
                res.setRGB(i, j, newColor.getRGB());
            }
        }

        return res;
    }

    @Override
    public String getName() {
        return "Invert";
    }
}
